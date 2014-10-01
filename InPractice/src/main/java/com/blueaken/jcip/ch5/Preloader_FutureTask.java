package com.blueaken.jcip.ch5;

import java.util.concurrent.*;

/**
 * Author: blueaken
 * Date: 9/30/14 10:48 上午
 */
public class Preloader_FutureTask {
    public static void main(String[] args) {
        ProductInfoCallable_ForFutureTask callable1 = new ProductInfoCallable_ForFutureTask(1000);
        ProductInfoCallable_ForFutureTask callable2 = new ProductInfoCallable_ForFutureTask(2000);

        FutureTask<ProductInfo> futureTask1 = new FutureTask<ProductInfo>(callable1);
        FutureTask<ProductInfo> futureTask2 = new FutureTask<ProductInfo>(callable2);

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
                    System.out.println("FutureTask1 output Product id = "+futureTask1.get().getId());
                }

                System.out.println("Waiting for FutureTask2 to complete");
                ProductInfo s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if(s !=null){
                    System.out.println("FutureTask2 output Product id = "+s.getId());
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
