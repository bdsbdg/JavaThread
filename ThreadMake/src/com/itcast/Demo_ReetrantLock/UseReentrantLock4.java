package com.itcast.Demo_ReetrantLock;


import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantLock4 {

    Lock lock = new ReentrantLock();    // 可以替代synchronized

    void func1(){
        /*
        * 需要加锁的代码开始前写调用lock()
        * 必须手动解锁 最好在finally中unlock()   该锁在异常的情况下不会释放掉锁
        *
        * */
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
                if(i==3){
                    func2();
                }
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

    void fun3(long timeout){
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(timeout, TimeUnit.SECONDS);
            System.out.println("func3执行 是否拿到锁："+isLocked);
            /*
            * tryLock()在指定时间内拿不到锁时就不拿了 继续向下执行 返回是否拿到锁
            * */
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(isLocked){
                lock.unlock();
            }
        }
    }

    void func4(){
        // 无限sleep
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    void func5(){
        try {
            // System.out.println("当前线程中断标记为:"+Thread.interrupted());  // 取出中断标记后会将中断标记重置
            lock.lockInterruptibly();   // 该方法就是内部进行了中断标记的判断    如果在上面先一步获取中断标记 那么该方法获取的就是重置后的标记  需要在另一线程中再设置一次线程中断
            // lockInterruptibly()当拿不到锁且线程中断为true时 将抛出异常InterruptedException
            // lock() 不会check线程中断状态
            // 线程中断为true时 且线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常
            System.out.println("func5 拿到锁");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            try {
                lock.unlock();
            }catch (Exception e){
                System.out.println("未拿到锁 被打断");
            }
        }
    }

    @Test
    public void Demo() throws InterruptedException {
        /*
         * ReentrantLock 可重入性验证
         * */
        UseReentrantLock4 u = new UseReentrantLock4();
        Thread t1 = new Thread(u::func1);
        t1.start();
        t1.join();
    }

    @Test
    public void Demo2() throws InterruptedException {
        /*
        * ReentrantLock的指定时间获取锁
        * */
        UseReentrantLock4 u = new UseReentrantLock4();
        Thread t1 = new Thread(u::func1);
        t1.start();
        Thread t2 = new Thread(() -> u.fun3(3));
        t2.start();
        t1.join();
        t2.join();
        t2.interrupt();
    }

    @Test
    public void Dem3() throws InterruptedException {
        /*
        * 手动中断锁的获取
        * */
        UseReentrantLock4 u = new UseReentrantLock4();
        Thread t1 = new Thread(u::func4);
        Thread t2 = new Thread(u::func5);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
//        t2.interrupt();     // 设置该线程中断标记为true
        TimeUnit.SECONDS.sleep(3);
        t2.interrupt();     // 设置该线程中断标记为true
        t1.join();
    }
}
