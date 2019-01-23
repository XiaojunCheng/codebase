package com.codebase.framework.systemdesign.delayqueue;

import java.util.Date;

/**
 * @author Xiaojun.Cheng
 * @date 2019/1/2
 */
public class DelayQueueTest {

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
        System.out.println(currentTimeInSec + ", " + generateTimeInSec + ", " + intervalInSec);
    }
}
