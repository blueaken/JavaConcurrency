package com.blueaken.playground.graceresume;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author jianshen
 */
public class SampleListener {
    private Thread thread = null;
    private SampleProcessor processor = null;
    private List<String> threads= new ArrayList();

    public void init(){
        processor = new SampleProcessor();

        thread = new Thread(processor);
        System.out.println("Starting Thread:" + thread.getName());
        thread.start();
        System.out.println("Background process successfully started.");

    }

    public void filpThreadSuspendedStatus() {
        //flip thread suspended status
        System.out.println("Start fliping processor thread suspended status in thread: " + Thread.currentThread().getName());
        if (processor.isThreadSuspended()){
            processor.setThreadSuspended(false);
        }

        if (!processor.isThreadSuspended()){
            notify();
        }
    }

    public void destroy(){
        System.out.println("Start stopping processor thread in thread: " + Thread.currentThread().getName());

        processor.terminate();
        try{
            thread.join();
            System.out.println("Thread successfully stopped. " + thread.getName());
        } catch (InterruptedException ie){
            System.out.println("Thread Join Interrupted ... " + Thread.currentThread().getName());
        }


    }


    /*
    * multiple threads version
    * */
//    public void init(){
//        processor = new SampleProcessor();
//        for (int i=0;i<5;i++){
//            thread = new Thread(processor);
//            threads.add(thread.getName());
//            System.out.println("Starting Thread:" + thread.getName());
//            thread.start();
//            System.out.println("Background process successfully started.");
//        }
//    }
//
//    public void filpThreadSuspendedStatus() {
//        if (threads != null){
//            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();//get the current active threads list
//            Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//
//            //flip thread suspended status
//            System.out.println("Start fliping processor threads' thread suspended status in thread: " + Thread.currentThread().getName());
//            if (processor.isThreadSuspended()){
//                processor.setThreadSuspended(false);
//            }
//
//            for (String threadName : threads){//find if sample processor threads in the list
//                for (Thread t: threadArray){
//                    if (t.getName().equalsIgnoreCase(threadName)){
//                        try{
//                          notify();
//                          t.join();
//                        } catch (InterruptedException ie){
//                            System.out.println("Thread Join Interrupted ... " + Thread.currentThread().getName());
//                        }
//                    }
//                }
//            }
//        }
//
//    }
}
