package com.blueaken.jcip.ch5;

import java.math.BigInteger;

/**
 * Author: blueaken
 * Date: 9/29/14 9:36 上午
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
// after deep thought...
        return new BigInteger(arg);
    }
}
