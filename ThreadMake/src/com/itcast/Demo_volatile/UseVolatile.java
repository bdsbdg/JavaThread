package com.itcast.Demo_volatile;

import org.junit.Test;

class TestDemo{
    boolean func1Flag = true;
    volatile boolean func2Flag = true;

    void func1(){
        try {
            Thread.sleep(2000);
            func1Flag = false;
            System.out.println("已修改func1Flag为"+func1Flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void func2(){
        try {
            Thread.sleep(2000);
            func2Flag = false;
            System.out.println("已修改func2Flag为"+func2Flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class UseVolatile {
    /*
    * while(flag)无限循环,开启线程两秒后将flag置为false 结束while循环
    * */

    @Test
    public void Demo1() throws InterruptedException {
        TestDemo td = new TestDemo();
        new Thread(td::func1).start();
        while (td.func1Flag){}
        System.out.println("结束");
    }

    @Test
    public void Demo2() throws InterruptedException {
        TestDemo td = new TestDemo();
        new Thread(td::func2).start();
        while (td.func2Flag){}
        System.out.println("结束");
    }
    /*
    * java中主内存中存放共享变量(实例变量，static字段和数组元素)
    * 每个线程都有自己独立的工作内存,对共享变量的操作都是从主内存中拷贝一份到工作内存中进行,操作完后写回主内存
    *
    * volatile修饰变量保证其可见性与有序性,使用cpu缓存一致性协议( MESI )
    * 多volatile修饰的变量的访问都必须从主内存中读取后操作,用完后写回主内存
    * */
}
