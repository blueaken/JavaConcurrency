package com.blueaken.playground.multithread.performancetest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author jianshen
 */
public class Synchronization_SingleThread_Test {
    private static final int COUNT = 1000000;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.US);

    private static final ThreadLocal<SimpleDateFormat> SDF_TL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.US);
        }
    };

    Map<String, Long> times = new HashMap<String, Long>();

    private Date sampleDate() {
        return new Date(1419012636622L);  // Use constant value so tests are performing same calculation.
    }

    private void reportTime(long startTime, String test) {

        long time = System.currentTimeMillis() - startTime;
        long totalTime = times.get(test) == null ? time : times.get(test) + time;
        times.put(test, totalTime);

        System.out.println("Time to run " + test + " " + COUNT + " times = " + time);
    }

    // -- 4 approaches

    private String usingSync() {
        synchronized (SDF) {
            return SDF.format(sampleDate());
        }
    }

    private String usingNew() {
        SimpleDateFormat local = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.US);
        return local.format(sampleDate());
    }

    private String usingThreadLocal() {
        return SDF_TL.get().format(sampleDate());
    }

    // Not thread safe.
    private String usingStatic() {
        return SDF.format(sampleDate());
    }

    // -- Runner

    public static void main(String [] args) {

        Synchronization_SingleThread_Test t = new Synchronization_SingleThread_Test();
        long startTime;

        for (int outer=0; outer<10; outer++) {

            startTime = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                t.usingSync();
            }
            t.reportTime(startTime, "usingSync");

            startTime = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                t.usingNew();
            }
            t.reportTime(startTime, "usingNew");

            startTime = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                t.usingThreadLocal();
            }
            t.reportTime(startTime, "usingThreadLocal");

            startTime = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                t.usingStatic();
            }
            t.reportTime(startTime, "usingStatic");

            System.out.println("");
        }

        System.out.println("Total time for usingSync (10) = " + t.times.get("usingSync"));
        System.out.println("Total time for usingNew (10) = " + t.times.get("usingNew"));
        System.out.println("Total time for usingThreadLocal (10) = " + t.times.get("usingThreadLocal"));
        System.out.println("Total time for usingStatic (10) = " + t.times.get("usingStatic"));
    }
}
