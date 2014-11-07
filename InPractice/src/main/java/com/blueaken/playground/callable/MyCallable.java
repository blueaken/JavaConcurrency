package com.blueaken.playground.callable;

import java.util.concurrent.Callable;

/**
 * @author jianshen
 */
public class MyCallable implements Callable<String>{

    public String call() throws Exception {
        Thread.sleep(2000);

        // return name of thread.
        return (Thread.currentThread().getName());
    }

}
