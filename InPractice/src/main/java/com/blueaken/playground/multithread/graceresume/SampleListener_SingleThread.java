package com.blueaken.playground.multithread.graceresume;

/**
 * @author jianshen
 */
public class SampleListener_SingleThread {
    private Thread thread = null;
    private SampleProcessor processor = null;

    /*
    * single thread version
    * */
    public void init(){
        processor = new SampleProcessor();

        thread = new Thread(processor);
        System.out.println("Starting Thread:" + thread.getName());
        thread.start();
        System.out.println("Background process successfully started.");

    }

    public synchronized void filpThreadSuspendedStatus() {
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

}
