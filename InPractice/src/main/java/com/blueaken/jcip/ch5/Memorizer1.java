package com.blueaken.jcip.ch5;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: blueaken
 * Date: 9/28/14 8:51 上午
 */
public class Memorizer1<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;
    public Memorizer1(Computable<A, V> c) {
        this.c = c;
    }
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
