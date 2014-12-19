package com.blueaken.playground.multithread.performancetest;

/**
 * @author jianshen
 */
public class SynchronizationListener {
    private Thread thread = null;
    private SynchronizationProcessor processor = new SynchronizationProcessor();

    private static final int COUNT = 100000;

    public void init(){
        for (int i=0;i<COUNT;i++){
            thread = new Thread(processor);
//            System.out.println("Starting Thread for Sync SDF: " + thread.getName());
            thread.start();
            try{
                thread.join();
            }catch (InterruptedException ie){

            }
        }
    }

}
