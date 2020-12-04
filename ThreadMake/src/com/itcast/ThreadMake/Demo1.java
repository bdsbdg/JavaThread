package com.itcast.ThreadMake;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Demo1 extends Thread {
    // 使用继承Thread创建线程


    @Override
    public void run() {
        synchronized ("1"){
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("Demo1111111111");
            }
        }

    }

    @Test
    public void testDemo1() throws InterruptedException {
        Demo1 demo1 = new Demo1();
        demo1.start();
        for (int i = 0; i < 10; i++) {

            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo1.join();
            System.out.println("tsettesttsettesttsettesttsettest");
        }
    }

}
