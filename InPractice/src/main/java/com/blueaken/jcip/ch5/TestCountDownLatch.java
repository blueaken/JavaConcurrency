package com.blueaken.jcip.ch5;

import java.util.concurrent.CountDownLatch;

/**
 * Author: blueaken
 * Date: 9/26/14 3:24 下午
 */
public class TestCountDownLatch {
    public static long timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    }
                    catch (InterruptedException ignored) {
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }

    public static void main(String[] args){
        SomeTask someTask = new SomeTask();
        try {
            long elapsedTime = timeTasks(3, someTask);
            long seconds = elapsedTime / 1000;
            System.out.println("total elapsed seconds :" + seconds);
        } catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }

    static class SomeTask implements Runnable{
        private int counter = 0;
        public void run() {
            counter++;
            System.out.println("SomeTask : Counter : " + counter);
        }
    }

}

