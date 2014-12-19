package com.blueaken.playground.multithread.performancetest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author jianshen
 */
public class ThreadLocalProcessor implements Runnable {
    private static final ThreadLocal<SimpleDateFormat> SDF_TL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.US);
        }
    };

    private Date sampleDate() {
        return new Date(1419012636622L);  // Use constant value so tests are performing same calculation.
    }

    @Override
    public void run(){
        SDF_TL.get().format(sampleDate());
    }
}
