package com.blueaken.jcip.ch5;

import java.util.concurrent.Callable;

/**
 * Author: blueaken
 * Date: 9/30/14 7:19 下午
 */
public class MyCallable_ForFutureTask implements Callable<String>{
    private long waitTime;

    public MyCallable_ForFutureTask (int timeInMillis){
        this.waitTime=timeInMillis;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }
}

