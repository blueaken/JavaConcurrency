package com.blueaken.playground.graceresume;

/**
 * @author jianshen
 */
public class TestSample {
    public static void main(String[] args){
//        SampleListener_SingleThread listener = new SampleListener_SingleThread();
        SampleListener_MultipleThreads listener = new SampleListener_MultipleThreads();

        long startTime = System.currentTimeMillis();

        listener.init();
        listener.filpThreadSuspendedStatus();
//        listener.destroy();

        long endTime = System.currentTimeMillis();

        System.out.println("");
        System.out.println("Total during time: " + (endTime - startTime) + " milliseconds");
    }
}
