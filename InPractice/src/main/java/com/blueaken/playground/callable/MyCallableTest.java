package com.blueaken.playground.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author jianshen
 */
public class MyCallableTest {
    public static void main (String args[]) {
        long startTime = System.currentTimeMillis();

        // thread pool size 10
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // collection to store results
        List<Future<String>> list = new ArrayList<Future<String>>();

        // spawn 10 jobs
        for(int i=0; i< 10; i++){
            Future<String> future = executor.submit(new MyCallable());
            list.add(future); //best way to use future and callable, if instead, trying to analyze the result back here
                              //then it will wait till each thread complete, which is back to single thread mode. Sometimes
                              //may need to add Thread.sleep() here to ensure the complete of every thread.
        }

        // print results
        for(Future<String> future : list){
            try {
                System.out.println(future.get());
            } catch (Exception e) {
            }
        }

        executor.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("");
        System.out.println("total processing time for 10 threads, thread pool size 10, is: " + (endTime - startTime) + " milliseconds.");
    }
}
