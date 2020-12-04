package com.itcast.ThreadMake;


import org.junit.Test;

import java.util.concurrent.*;

public class Demo3 implements Runnable{


    @Test
    public void testDemo3() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<?> submit = pool.submit(new Demo3());
        submit.get();
//        TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Demo33333333");
        }
    }

    @Test
    public void Demo12() throws InterruptedException {
        Demo1 t1 = new Demo1();

        Demo2 demo2 = new Demo2();
        Thread t2 = new Thread(demo2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
