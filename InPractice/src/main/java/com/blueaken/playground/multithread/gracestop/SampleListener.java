package com.blueaken.playground.multithread.gracestop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author jianshen
 */
public class SampleListener {
    private Thread thread = null;
    private SampleProcessor processor = new SampleProcessor();
    private List<String> threads= new ArrayList();

    public void init(){
        for (int i=0;i<5;i++){
            thread = new Thread(processor);
            threads.add(thread.getName());
            System.out.println("Starting Thread:" + thread.getName());
            thread.start();
            System.out.println("Background process successfully started.");
        }
    }

    public void destroy(){
        System.out.println("Start stopping processor threads in thread: " + Thread.currentThread().getName());
        if (threads != null){
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();//get the current active threads list
            Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);

            processor.terminate();//processor is good enough to stop only once, no need to put into the loop

            for (String threadName : threads){//find if sample processor threads in the list
                for (Thread t: threadArray){
                    if (t.getName().equalsIgnoreCase(threadName)){
                        try{
                            t.join();
                            System.out.println("Thread successfully stopped. " + t.getName());
                        } catch (InterruptedException ie){
                            System.out.println("Thread Join Interrupted ... " + Thread.currentThread().getName());
                        }
                    }
                }
            }
        }
    }

}
