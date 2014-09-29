package com.blueaken.jcip.ch5;

/**
 * Author: blueaken
 * Date: 9/29/14 9:35 上午
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
