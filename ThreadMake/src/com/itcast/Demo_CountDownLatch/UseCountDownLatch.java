package com.itcast.Demo_CountDownLatch;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {

    @Test
    public void Demo1() throws InterruptedException {
        Thread[] threads = new Thread[10];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                latch.countDown();
                System.out.println("i");
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        latch.await();      // 阻塞住

        /*
        * latch.await()将在latch中维护的值为0时停止阻塞
        * latch.countDown()将里面维护的值-1
        * */

    }
}
