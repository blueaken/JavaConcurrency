package com.blueaken.jcip.ch5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: blueaken
 * Date: 9/29/14 9:33 上午
 */
public class Memorizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;
    public Memorizer2(Computable<A, V> c) { this.c = c; }
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

