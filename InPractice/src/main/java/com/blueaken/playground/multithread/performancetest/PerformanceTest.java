package com.blueaken.playground.multithread.performancetest;

/**
 * @author jianshen
 */
public class PerformanceTest {
    public static void main(String[] args){
        SynchronizationListener synchronizationListener = new SynchronizationListener();
        long syncRunTime = synchronizationListener.timeTasks();
        System.out.println("");
        System.out.println("Time to run usingSync with 10000 threads: " + syncRunTime + " milliseconds");

        ThreadLocalListerner threadLocalListerner = new ThreadLocalListerner();
        long threadLocalRunTime = threadLocalListerner.timeTasks();
        System.out.println("");
        System.out.println("Time to run usingThreadLocal with 10000 threads: " + threadLocalRunTime + " milliseconds");
    }
}
