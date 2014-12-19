package com.blueaken.playground.multithread.performancetest;

/**
 * @author jianshen
 */
public class PerformanceTest {
    public static void main(String[] args){
        SynchronizationListener synchronizationListener = new SynchronizationListener();

        long startTime = System.currentTimeMillis();
        synchronizationListener.init();
        long endTime = System.currentTimeMillis();

        System.out.println("");
        System.out.println("Time to run usingSync with 10000 threads: " + (endTime - startTime) + " milliseconds");

        ThreadLocalListerner threadLocalListerner = new ThreadLocalListerner();

        startTime = System.currentTimeMillis();
        threadLocalListerner.init();
        endTime = System.currentTimeMillis();

        System.out.println("");
        System.out.println("Time to run usingThreadLocal with 10000 threads: " + (endTime - startTime) + " milliseconds");
    }
}
