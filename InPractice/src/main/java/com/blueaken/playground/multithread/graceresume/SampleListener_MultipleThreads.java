package com.blueaken.playground.multithread.graceresume;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author jianshen
 */
public class SampleListener_MultipleThreads {
    private Thread thread = null;
    private SampleProcessor processor = null;
    private List<String> threads= new ArrayList();

    /*
   * multiple threads version
   * */
    public void init(){
        processor = new SampleProcessor();
        for (int i=0;i<3;i++){
            thread = new Thread(processor);
            threads.add(thread.getName());
            System.out.println("Starting Thread:" + thread.getName());
            thread.start();
            System.out.println("Background process successfully started.");
        }
    }

    public synchronized void filpThreadSuspendedStatus() {
        if (threads != null){
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();//get the current active threads list
            Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);

            //flip thread suspended status
            System.out.println("Start fliping processor threads' thread suspended status in thread: " + Thread.currentThread().getName());
            if (processor.isThreadSuspended()){
                processor.setThreadSuspended(false);
            }

            for (String threadName : threads){//find if sample processor threads in the list
                for (Thread t: threadArray){
                    if (t.getName().equalsIgnoreCase(threadName)){
                        notify(); // no need for notifyAll() here, since inside for loop
                    }
                }
            }
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
