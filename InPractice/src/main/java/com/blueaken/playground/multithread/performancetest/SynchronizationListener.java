package com.blueaken.playground.multithread.performancetest;

import java.util.concurrent.CountDownLatch;

/**
 * @author jianshen
 */
public class SynchronizationListener {
    private Thread thread = null;
    private SynchronizationProcessor processor = new SynchronizationProcessor();

    private static final int COUNT = 10000;
    final CountDownLatch startGate = new CountDownLatch(1);
    final CountDownLatch endGate = new CountDownLatch(COUNT);

    public long timeTasks(){
        for (int i=0;i<COUNT;i++){
            thread = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            processor.run();
                        } finally {
                            endGate.countDown();
                        }
                    }
                    catch (InterruptedException ignored) {
                    }
                }
            };
//            System.out.println("Starting Thread for ThreadLocal SDF: " + thread.getName());
            thread.start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        try{
            endGate.await();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return end-start;

    }

}
