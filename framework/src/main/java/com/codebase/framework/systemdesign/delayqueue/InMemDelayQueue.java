package com.codebase.framework.systemdesign.delayqueue;

import sun.misc.Timer;

import java.util.Date;
import java.util.Set;

/**
 * <href>https://chuansongme.com/n/1673795846413</href>
 *
 * @author Xiaojun.Cheng
 * @date 2018/12/26
 */
public class InMemDelayQueue {

    /**
     * 1小时3600秒
     */
    private static final int SLOT_NUM = 3600;
    /**
     * 3600秒
     */
    private final Slot[] slots;
    /**
     * 当前索引
     */
    private volatile int currentIndex = 0;

    private final Timer timer;

    public InMemDelayQueue() {
        slots = new Slot[SLOT_NUM];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
        timer = new Timer(owner -> {
            currentIndex = (currentIndex + 1) % SLOT_NUM;
            Set<Task> tasks = task();
            tasks.forEach(t -> t.execute());
        }, 1000);
        timer.cont();
    }

    public void add(Task task, int delayInSec) {
        int finalIndex = currentIndex + delayInSec;
        int roundIndex = finalIndex % SLOT_NUM;
        int cycleNum = finalIndex / SLOT_NUM;
        TaskContext taskContext = new TaskContext();
        taskContext.setCycleNum(cycleNum);
        slots[roundIndex].add(task, taskContext);
    }

    public Set<Task> task() {
        Slot slot = slots[currentIndex];
        Set<Task> tasks = slot.filter();
        slot.reset();
        return tasks;
    }

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        long timeInSec = date.getTime() / 1000;
        System.out.println(timeInSec + ", date: " + date);

        InMemDelayQueue delayQueue = new InMemDelayQueue();
        delayQueue.add(new PrintTask(20), 20);
        delayQueue.add(new PrintTask(12), 12);

        Thread.sleep(2000);
        delayQueue.add(new PrintTask(3), 3);
    }

}

class PrintTask implements Task {

    private final long generateTimeInSec;
    private final int intervalInSec;

    public PrintTask(int intervalInSec) {
        this.generateTimeInSec = System.currentTimeMillis() / 1000;
        this.intervalInSec = intervalInSec;
    }

    @Override
    public void execute() {
        long currentTimeInSec = System.currentTimeMillis() / 1000;
        ;
        System.out.println(currentTimeInSec + ", " + generateTimeInSec + ", " + intervalInSec);
    }
}
