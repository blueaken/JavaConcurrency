package com.blueaken.jcip.ch5;

import java.util.concurrent.*;

/**
 * Author: blueaken
 * Date: 9/30/14 7:21 下午
 */
public class FutureTaskExample {

    public static void main(String[] args) {
        MyCallable_ForFutureTask callable1 = new MyCallable_ForFutureTask(1000);
        MyCallable_ForFutureTask callable2 = new MyCallable_ForFutureTask(2000);

        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        // Creates a thread pool that reuses a fixed number of threads operating
        // off a shared unbounded queue. At any point, at most nThreads threads
        // will be active processing tasks.
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true) {
            try {
                if(futureTask1.isDone() && futureTask2.isDone()){
                    System.out.println("Done");
                    //shut down executor service
                    executor.shutdown();
                    return;
                }

                if(!futureTask2.isDone()){
                    //wait indefinitely for future task to complete
                    System.out.println("FutureTask1 output="+futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if(s !=null){
                    System.out.println("FutureTask2 output="+s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch(TimeoutException e){
                //do nothing
            }
        }

    }

}
