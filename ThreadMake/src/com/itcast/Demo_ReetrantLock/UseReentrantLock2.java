package com.itcast.Demo_ReetrantLock;

import org.junit.Test;

import java.beans.Transient;

public class UseReentrantLock2 {
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
            if(i==3){
                m2();
            }
        }
    }

    synchronized void m2(){
        // 锁对象this
        System.out.println("m2.........");
    }


    @Test
    public void Demo() throws InterruptedException {
        UseReentrantLock2 u1 = new UseReentrantLock2();
        Thread t1 = new Thread(u1::m1);
        t1.start();
        t1.join();
    }

    /*
    * synchronized 是可重入的
    *       同一线程内访问加锁的代码且锁是同一对象的情况
    * */

}
