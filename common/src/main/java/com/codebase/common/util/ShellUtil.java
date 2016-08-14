package com.codebase.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ShellUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShellUtil.class);

    public static String execCommand(Map<String, String> env, String... cmd) throws IOException {
        ShellUtil exec = new ShellUtil(cmd, env);
        exec.execute();
        return exec.getOutput();
    }

    // 命令 & 相关配置
    private final String[] command;
    private File workDir;
    private Map<String, String> environment;

    // 执行过程相关
    private Process process;
    private int exitCode;
    private String output = StringUtil.EMPTY;

    private ShellUtil(String[] execString) {
        this(execString, null, null);
    }

    private ShellUtil(String[] execString, Map<String, String> environment) {
        this(execString, null, environment);
    }

    private ShellUtil(String[] execString, File workDir) {
        this(execString, workDir, null);
    }

    public ShellUtil(String[] execString, File workDir, Map<String, String> environment) {
        this.command = execString.clone();
        this.workDir = workDir;
        this.environment = environment;
    }

    public void execute() throws IOException {
        exitCode = 0; //reset
        executeCommand();
    }

    private void executeCommand() throws IOException {

        ProcessBuilder builder = createProcessBuilder();
        boolean completed = false;
        process = builder.start();
        final BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        final StringBuilder errorInfo = new StringBuilder();
        Thread errorThread = new Thread() {
            @Override
            public void run() {
                try {
                    String line;
                    while (((line = errorStream.readLine()) != null) && !isInterrupted()) {
                        errorInfo.append(line);
                        errorInfo.append(System.getProperty("line.separator"));
                    }
                } catch (IOException ioe) {
                    LOGGER.warn("Error in reading error stream", ioe);
                }
            }
        };
        try {
            errorThread.start();
        } catch (Exception ignored) {
        }

        final BufferedReader outputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
        try {
            processOutput(outputStream);
            try {
                errorThread.join();
            } catch (Exception e) {
                LOGGER.warn("Exception in joining error stream", e);
            }
            exitCode = process.waitFor();
            completed = true;
            if (exitCode != 0 || errorInfo.length() != 0) {
                throw new ExitCodeException(exitCode, errorInfo.toString());
            }
        } catch (InterruptedException ie) {
            throw new IOException(ie.toString());
        } finally {
            try {
                outputStream.close();
            } catch (IOException ioe) {
                LOGGER.warn("Error in closing input stream", ioe);
            }
            if (!completed) {
                errorThread.interrupt();
            }
            try {
                errorStream.close();
            } catch (IOException ioe) {
                LOGGER.warn("Error while closing the error stream", ioe);
            }
            process.destroy();
        }
    }

    private ProcessBuilder createProcessBuilder() {
        ProcessBuilder builder = new ProcessBuilder(command);
        if (environment != null) {
            builder.environment().putAll(environment);
        }
        if (workDir != null) {
            builder.directory(workDir);
        }
        return builder;
    }

    public String getOutput() {
        return output;
    }

    public int getExitCode() {
        return exitCode;
    }

    public Process getProcess() {
        return process;
    }

    private void processOutput(BufferedReader stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] buf = new char[512];
        int nRead;
        while ((nRead = stream.read(buf, 0, buf.length)) > 0) {
            builder.append(buf, 0, nRead);
        }
        output = builder.toString();
    }


    static class ExitCodeException extends IOException {

        private static final long serialVersionUID = -3461928174760577599L;

        public ExitCodeException(int exitCode, String message) {
            super("exit code: " + exitCode + ", message: " + message);
        }
    }

}
