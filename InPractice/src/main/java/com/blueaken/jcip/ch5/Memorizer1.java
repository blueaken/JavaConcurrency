package com.blueaken.jcip.ch5;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: blueaken
 * Date: 9/28/14 8:51 上午
 */
interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
// after deep thought...
        return new BigInteger(arg);
    }
}

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
