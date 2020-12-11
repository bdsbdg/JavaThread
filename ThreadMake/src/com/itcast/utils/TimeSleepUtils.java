package com.itcast.utils;

import java.util.concurrent.TimeUnit;

public class TimeSleepUtils {
    public static void sleep(int s){
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
