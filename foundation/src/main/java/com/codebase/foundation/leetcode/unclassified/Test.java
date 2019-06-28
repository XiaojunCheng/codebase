package com.codebase.foundation.leetcode.unclassified;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengxiaojun
 * @date 2019-06-17
 */
public class Test {

    public static final Object LOCK = new Object();

    public static final AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        int threadNum = 9;
        int maxNum = 65;

        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(new RunTask(i, threadNum, maxNum));
            threads[i].setName("thread-" + i);
        }

        for (int i = 0; i < threadNum; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadNum; i++) {
            threads[i].join();
        }

        System.out.println("finish");
    }

    static class RunTask implements Runnable {

        private final int threadId;
        private final int threadNum;
        private final int maxNum;

        public RunTask(int threadId, int threadNum, int maxNum) {
            this.threadId = threadId;
            this.threadNum = threadNum;
            this.maxNum = maxNum;
        }

        @Override
        public void run() {

            while (true) {
                synchronized (LOCK) {
                    while (value.get() % threadNum != threadId) {
                        //最大值跳出循环
                        if (value.get() >= maxNum) {
                            break;
                        }

                        try {
                            //如果不是，则当前线程进入wait
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //最大值跳出循环
                    if (value.get() >= maxNum) {
                        break;
                    }

                    System.out.println("threadId: " + threadId + " : " + value.getAndIncrement());

                    // 唤醒其他wait线程
                    LOCK.notifyAll();
                }
            }

        }
    }


}
