package com.itcast.Demo_Phaser;

import com.itcast.utils.TimeSleepUtils;

import javax.naming.Name;
import java.util.concurrent.Phaser;

public class Player extends Thread {
    String name;
    int capacity;
    Phaser phaser;

    Player(String name,int capacity,Phaser phaser){
        super();
        this.name = name;
        this.capacity = capacity;
        this.phaser = phaser;
    }

    public void station1(){
        // 震颤的大地
        TimeSleepUtils.sleep(1);
        System.out.println(name+"-通关黑雾之源");
        phaser.arriveAndAwaitAdvance();
    }

    public void station2(){
        // 擎天之柱
        TimeSleepUtils.sleep(1);
        System.out.println(name+"-通关擎天之柱");
        phaser.arriveAndAwaitAdvance();
    }

    public void station3(){
        // 黑色火山
        if(capacity>999999){
            TimeSleepUtils.sleep(3);
            System.out.println(name+"-通关黑色火山");
            phaser.arriveAndAwaitAdvance();
        }else {
            System.out.println(name+"-街的站");
            phaser.arriveAndDeregister();
        }
    }

    public void station4(){
        // 黑色火山
        if(capacity>999999){
            TimeSleepUtils.sleep(1);
            System.out.println(name+"-通关安图恩的心脏  翻牌！");
            System.out.println("=="+phaser.getRegisteredParties());
//            System.out.println("++"+phaser.getUnarrivedParties());

            phaser.arriveAndAwaitAdvance();
        }else {
//            phaser.register();
            System.out.println(name+"-等翻牌了");
//            phaser.arriveAndAwaitAdvance();
//            phaser.arriveAndDeregister();
        }
    }

    public void station5(){
        System.out.println(name+"-翻牌中 少话！");
        phaser.arriveAndAwaitAdvance();
    }

    @Override
    public void run() {
        station1();
        station2();
        station3();
        station4();
        station5();
    }
}
