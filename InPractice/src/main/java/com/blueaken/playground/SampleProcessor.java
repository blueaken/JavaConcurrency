package com.blueaken.playground;

/**
 * @author jianshen
 */
public class SampleProcessor implements Runnable{
    private volatile boolean running = true;

    public void terminate(){
        running = false;
    }

    @Override
    public void run(){
        while (running){
            try{
                System.out.println("Sleeping ... " + Thread.currentThread().getName());
                Thread.sleep(10000l);
                System.out.println("Processing ... " + Thread.currentThread().getName());
            } catch (InterruptedException ie){
                System.out.println("Thread Process Interrupted ... " + Thread.currentThread().getName());
                running = false;
            }
        }
    }
}
