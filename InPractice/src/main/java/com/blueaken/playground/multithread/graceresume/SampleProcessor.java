package com.blueaken.playground.multithread.graceresume;

/**
 * @author jianshen
 */
public class SampleProcessor implements Runnable{
    private volatile boolean running = true;
    private volatile boolean threadSuspended = true;

    public boolean isThreadSuspended() {
        return threadSuspended;
    }

    public void setThreadSuspended(boolean threadSuspended) {
        this.threadSuspended = threadSuspended;
    }

    public synchronized void terminate(){
        running = false;
        /*
        *  the target thread may already be suspended at the time that another thread tries to stop it. If the terminate
         *  method merely sets the state variable (running) to false, the target thread will remain suspended (waiting
         *  on the monitor), rather than exiting gracefully as it should.
        * */
//        notify();
        notifyAll(); //for multiple threads version listener
    }

    @Override
    public void run(){
        while (running){
            try{
                System.out.println("Waiting ... " + Thread.currentThread().getName());

                Thread.sleep(5000L);

                synchronized(this) {
                    while (threadSuspended && running)
                        wait();
                }

                System.out.println("Page Repainting ... " + Thread.currentThread().getName());

            } catch (InterruptedException ie){
                System.out.println("Thread Process Interrupted ... " + Thread.currentThread().getName());
                running = false;
            }
        }
    }
}
