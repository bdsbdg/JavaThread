package com.itcast.Demo_CycliBarrier;

// Cyclic循环的 Barrier屏障

import com.itcast.utils.TimeSleepUtils;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class MyThread extends Thread{
    CyclicBarrier cyclicBarrier;
    MyThread(CyclicBarrier cyclicBarrier){
        super();
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("thread-"+this.getName()+"-await()");
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("thread-"+this.getName()+"-Over");
    }
}

public class UseCyclicBarrier {

    @Test
    public void Demo1() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20);

        Thread[] threads = new Thread[30];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                TimeSleepUtils.sleep(1);
                System.out.println("await()");
                try {
                    cyclicBarrier.await();
                    // 到这里要凑够二十个线程调用await才会停止阻塞
                    // 不满就一直等着
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("====over====");
            },i+"");
        }

        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println("thread-main到达");
        cyclicBarrier.await();
        System.out.println("thread-main====over====");

        // 有十个线程在等待cyclicBarrier的await阻塞结束
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void Demo2() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("停止调用await()的20个线程");
            }
        });

        Thread[] threads = new Thread[30];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(cyclicBarrier);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        /*
        * 构造时传入Runnable时 满人执行一次
        * */
    }

    /*
    * 多槽治具同时进 不同时测完 同时出
    * */
}
