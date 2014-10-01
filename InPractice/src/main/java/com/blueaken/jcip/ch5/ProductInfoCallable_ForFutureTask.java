package com.blueaken.jcip.ch5;

import java.util.concurrent.Callable;

/**
 * Author: blueaken
 * Date: 9/30/14 7:19 下午
 */
public class ProductInfoCallable_ForFutureTask implements Callable<ProductInfo>{
    private long waitTime;

    public ProductInfoCallable_ForFutureTask(int timeInMillis){
        this.waitTime=timeInMillis;
    }
    @Override
    public ProductInfo call() throws Exception {
        Thread.sleep(waitTime);

        ProductInfo productInfo = new ProductInfo();
        productInfo.setId("12345");
        productInfo.setName("name");
        productInfo.setDescription("description");

        return productInfo;
    }
}

