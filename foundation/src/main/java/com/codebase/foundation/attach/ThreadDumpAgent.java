package com.codebase.foundation.attach;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Xiaojun.Cheng
 * @date 2018/7/30
 */
public class ThreadDumpAgent extends TimerTask {

// configuration & defaults
    /**
     * turn on debugging
     */
    private boolean debug = false;
    /**
     * where to save dumps
     */
    private String rootPath = "tmp";
    /**
     * interval between checks in milliseconds
     */
    private int interval = 1000;
    /**
     * how long thread needs to be blocked to save the dump in milliseconds
     */
    private int threshold = 60000;
    /**
     * how long to wait between saving next dump in milliseconds
     */
    private int saveDelay = 60000;

// internal
    /**
     * a
     */
    private static final String NAME = "JVM Monitoring Agent";
    private final Timer timer = new Timer(NAME, true);
    private final WeakHashMap<Thread, Long> blockedThreads = new WeakHashMap<>();
    private long lastSave = 0;
    private long loopStartTime = 0;
    private String dumpFileName = "";
    private boolean blockedToLong = false;

    public static void premain(String stringArgs) throws InterruptedException {
        ThreadDumpAgent agent = new ThreadDumpAgent(stringArgs);
        agent.start();
    }

    public ThreadDumpAgent(String stringArgs) {
        parseArgs(stringArgs);
        createRootPath();
    }

    private void parseArgs(String stringArgs) {
        String[] args = stringArgs == null ? new String[0] : stringArgs.split(",");
        for (String arg : args) {
            String[] keyAndValue = arg.split("=", 2);
            switch (keyAndValue[0]) {
                case "":
                    break; // in case of no args just skip
                case "debug":
                    debug = true;
                    break;
                case "path":
                    rootPath = keyAndValue[1];
                    break;
                case "interval":
                    interval = Integer.parseInt(keyAndValue[1]);
                    break;
                case "threshold":
                    threshold = Integer.parseInt(keyAndValue[1]);
                    break;
                case "delay":
                    saveDelay = Integer.parseInt(keyAndValue[1]);
                    break;
                default:
                    log("Unknown argument:" + arg);
                    break;
            }
        }
        log(String.format(
                "Initiated with:%n  root: '%s'%n  interval: %d%n  treshold: %d%n",
                rootPath,
                interval,
                threshold
        ));
    }

    private void createRootPath() {
        try {
            Files.createDirectories(Paths.get(rootPath));
        } catch (IOException ex) {
            log(ex.toString());
        }
    }

    public void start() {
        run();
        timer.schedule(this, interval, interval);
    }

    @Override
    public void run() {
        loopStartTime = System.currentTimeMillis();
        boolean savedDump = checkThreads();
        long endTime = System.currentTimeMillis();
        long timeDiff = (endTime - loopStartTime);

        String msg = "-" + loopStartTime + "- It took " + timeDiff + "ms";
        if (blockedToLong)
            msg += " - threads are blocked";
        if (savedDump) {
            lastSave = loopStartTime;
            msg += " - saved dump: " + dumpFileName;
        }
        log(msg);
    }

    private void log(String msg) {
        if (debug)
            System.err.println("[" + NAME + "] " + msg);
    }

    private boolean checkThreads() {
        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();

        cleanUnBlockedThreads();
        addBlockedThreads(threads.keySet());
        checkBlockedToLong();

        return blockedToLong && shouldBeSaved() && saveThreadsDump(threads);
    }

    private void cleanUnBlockedThreads() {
        blockedThreads.keySet().removeIf(thread ->
                (thread.getState() != Thread.State.BLOCKED)
        );
    }

    private void addBlockedThreads(Set<Thread> threads) {
        threads.stream().filter(thread ->
                (thread.getState() == Thread.State.BLOCKED)
        ).forEach(thread ->
                blockedThreads.putIfAbsent(thread, loopStartTime)
        );
    }

    private void checkBlockedToLong() {
        long now = System.currentTimeMillis();
        blockedToLong = blockedThreads.values().stream().anyMatch(date ->
                (now - date > threshold)
        );
    }

    private boolean shouldBeSaved() {
        return lastSave + saveDelay <= loopStartTime;
    }

    private void printThreadsDump(PrintStream stream, Map<Thread, StackTraceElement[]> threads) {
        stream.format(
                "#Threads: %d, #Blocked: %d%n%n",
                threads.size(),
                blockedThreads.size()
        );
        threads.forEach((thread, stack) -> {
            stream.format(
                    "Thread:%d '%s' %sprio=%d %s%n",
                    thread.getId(),
                    thread.getName(),
                    thread.isDaemon() ? "deamon " : "",
                    thread.getPriority(),
                    thread.getState()
            );
            for (StackTraceElement line : stack)
                stream.println("        " + line);
            stream.println();
        });
    }

    private boolean saveThreadsDump(Map<Thread, StackTraceElement[]> threads) {
        dumpFileName = rootPath + "/threads_dump_" + loopStartTime + ".txt";

        try (PrintStream stream = new PrintStream(dumpFileName)) {
            printThreadsDump(stream, threads);
            return true;
        } catch (FileNotFoundException ex) {
            log(ex.toString());
            return false;
        }
    }
}
