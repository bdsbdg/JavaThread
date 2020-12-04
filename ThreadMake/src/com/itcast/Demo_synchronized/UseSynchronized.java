package com.itcast.Demo_synchronized;


import org.junit.Test;
/*
* synchronized 使用在方法上 与 使用在方法局部
* */
class TestDemo{
    int num = 0;

    synchronized void func1(){
        // synchronized修饰方法
        //      方法为静态时  锁对象为 该方法所在类.class
        //      方法为非静态时  锁对象为 this
        num++;
    }

    void func2(){
        synchronized (this){
            num++;
        }
    }
}

public class UseSynchronized {

    @Test
    public void demo1() throws InterruptedException {
        TestDemo td = new TestDemo();
        Thread thread1 = new Thread(()->{for (int i = 0; i < 100000; i++)td.func1();});
        Thread thread2 = new Thread(()->{for (int i = 0; i < 100000; i++)td.func2();});

        thread1.start();
        thread2.start();

        // join: 等待执行join的线程结束该线程才能继续向下执行
        thread1.join();
        thread2.join();

        System.out.println(td.num); // 200000
        /*
        * synchronized可以保证多个线程访问同一数据的可见性与原子性,相当于串行了已经是
        *
        * synchronize是可重入的  synchronize修饰的方法中又调用synchronize修饰的方法且锁是同一对象
        * synchronize修饰的代码中抛出异常时默认会释放锁   catch处理了不会释放
        * synchronize的锁升级   偏向锁 -> 自旋锁(轻量级锁) -> 重量级锁
        *
        * 执行时间短(加锁代码)  线程数少  => 自旋
        * 执行时间长            线程数多  => 系统锁
        * */
    }
}
