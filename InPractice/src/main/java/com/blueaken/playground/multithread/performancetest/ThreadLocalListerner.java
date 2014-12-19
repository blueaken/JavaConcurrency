package com.blueaken.playground.multithread.performancetest;

/**
 * @author jianshen
 */
public class ThreadLocalListerner {
    private Thread thread = null;
    private ThreadLocalProcessor processor = new ThreadLocalProcessor();

    private static final int COUNT = 100000;

    public void init(){
        for (int i=0;i<COUNT;i++){
            thread = new Thread(processor);
//            System.out.println("Starting Thread for ThreadLocal SDF: " + thread.getName());
            thread.start();
            try{
                thread.join();
            }catch (InterruptedException ie){

            }
        }
    }

}
