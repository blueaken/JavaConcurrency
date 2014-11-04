package com.blueaken.playground;

/**
 * @author jianshen
 */
public class TestSample {
    public static void main(String[] args){
        SampleListener listener = new SampleListener();

        long startTime = System.currentTimeMillis();
        listener.init();
        listener.destroy();
        long endTime = System.currentTimeMillis();

        System.out.println("");
        System.out.println("Total during time: " + (endTime - startTime) + " milliseconds");
    }
}
