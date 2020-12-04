package com.itcast.Demo_ReetrantLock;


import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantLock3 {
    Lock lock = new ReentrantLock();    // 可以替代synchronized

    void func1(){
        /*
        * 需要加锁的代码开始前写调用lock()
        * 必须手动解锁 最好在finally中unlock()   该锁在异常的情况下不会释放掉锁
        *
        * */
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void func2(){
        try{
            lock.lock();
            System.out.println("func2...");
        }finally {
            lock.unlock();
        }
    }

    @Test
    public void Demo() throws InterruptedException {
        UseReentrantLock3 u = new UseReentrantLock3();
        Thread t1 = new Thread(u::func1);
        Thread t2 = new Thread(u::func2);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        t1.join();
        t2.join();
    }

}
