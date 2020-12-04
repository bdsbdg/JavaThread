package com.itcast.Demo_LongAdder;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class UseLongAdder {
    static long num1 = 0L;
    static AtomicLong num2 = new AtomicLong();
    static LongAdder num3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 1000;
        int addCount = 10000;

//        int threadCount = 1000;
//        int addCount = 10000;
//        result:10000000>>synchronize用时:642
//        result:10000000>>AtomicLong用时:181
//        result:10000000>>LongAdder用时:115

//        int threadCount = 10000;
//        int addCount = 100000;
//        result:1000000000>>synchronize用时:8258
//        result:1000000000>>AtomicLong用时:21964
//        result:1000000000>>LongAdder用时:2331

        synchronizeAdd(threadCount,addCount);
        atomicLongAdd(threadCount,addCount);
        longAdderAdd(threadCount,addCount);

    }

    public static void synchronizeAdd(int threadCount, int addCount) throws InterruptedException {
        Thread[] t1 = new Thread[threadCount];
        for (int i = 0; i < t1.length; i++) {
            t1[i] = new Thread(()->{

                for (int k = 0; k < addCount; k++){
                    synchronized (UseLongAdder.class){
                        num1++;
                    }
                }
            });
        }

        long start = new Date().getTime();

        for (Thread thread : t1){
            thread.start();
        }
        for (Thread thread : t1){
            thread.join();
        }
        System.out.println("result:"+num1+">>synchronize用时:"+(new Date().getTime()-start));
    }

    public static void atomicLongAdd(int threadCount, int addCount) throws InterruptedException {
        Thread[] t2 = new Thread[threadCount];

        for (int i = 0; i < t2.length; i++) {
            t2[i] = new Thread(()->{
                for (int k = 0; k < addCount; k++) num2.incrementAndGet();
            });
        }

        long start2 = new Date().getTime();

        for (Thread thread : t2){
            thread.start();
        }
        for (Thread thread : t2){
            thread.join();
        }
        System.out.println("result:"+num2.get()+">>AtomicLong用时:"+(new Date().getTime()-start2));
    }

    public static void longAdderAdd(int threadCount, int addCount) throws InterruptedException {
        Thread[] t3 = new Thread[threadCount];

        for (int i = 0; i < t3.length; i++) {
            t3[i] = new Thread(()->{
                for (int k = 0; k < addCount; k++) num3.increment();
            });
        }

        long start3 = new Date().getTime();

        for (Thread thread : t3){
            thread.start();
        }
        for (Thread thread : t3){
            thread.join();
        }
        System.out.println("result:"+num3.longValue()+">>LongAdder用时:"+(new Date().getTime()-start3));
    }
}
