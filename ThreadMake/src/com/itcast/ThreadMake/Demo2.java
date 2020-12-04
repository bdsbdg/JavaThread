package com.itcast.ThreadMake;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Demo2 implements Runnable {
    @Override
    public void run() {
        synchronized ("1"){
            for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

                System.out.println("Demo2222222222");
            }
        }

    }

    @Test
    public synchronized void testDemo2() throws InterruptedException {
        Demo2 demo2 = new Demo2();
        Thread t2 = new Thread(demo2);
        t2.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("tsettesttsettesttsettesttsettest");
                }
            }
        });
        t1.start();

        t1.join();
        t2.join();
    }
}
