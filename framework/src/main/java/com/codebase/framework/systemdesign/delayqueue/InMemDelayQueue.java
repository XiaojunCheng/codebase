package com.codebase.framework.systemdesign.delayqueue;

import sun.misc.Timer;

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

}


