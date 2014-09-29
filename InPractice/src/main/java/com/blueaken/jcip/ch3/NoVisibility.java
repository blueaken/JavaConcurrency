package com.blueaken.jcip.ch3;

/**
 * @author jianshen
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }
    public static void main(String[] args) {
        for (int i=0; i<10; i++){
            new ReaderThread().start();
            number = 42;
            ready = true;
        }
    }
}