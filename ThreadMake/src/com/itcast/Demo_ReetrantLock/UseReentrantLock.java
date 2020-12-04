package com.itcast.Demo_ReetrantLock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class UseReentrantLock {
    // 锁的重入性
    synchronized void m1() {
        // 锁对象this
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2(){
        // 锁对象this
        System.out.println("m2.........");
    }

    @Test
    public void Demo() throws InterruptedException {
        UseReentrantLock u1 = new UseReentrantLock();
        Thread t1 = new Thread(u1::m1);
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(u1::m2);
        t2.start();
        t1.join();
        t2.join();

        /*
         * 开启了两条线程  锁对象是同一个
         * 此时必须等到先拿到锁的m1执行完毕m2才能执行
         * */
    }

}
