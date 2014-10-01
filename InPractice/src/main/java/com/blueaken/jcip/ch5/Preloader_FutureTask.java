package com.blueaken.jcip.ch5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Author: blueaken
 * Date: 9/30/14 10:48 上午
 */
public class Preloader_FutureTask {
    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                public ProductInfo call() throws DataLoadException {
                    return loadProductInfo();
                }
            });

    private final Thread thread = new Thread(future);

    public void start() { thread.start(); }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            else
                throw launderThrowable(cause);
        }
    }

    /** If the Throwable is an Error, throw it; if it is a
     * RuntimeException return it, otherwise throw IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    public ProductInfo loadProductInfo(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId("12345");
        productInfo.setName("name");
        productInfo.setDescription("description");

        return productInfo;
    }

}
