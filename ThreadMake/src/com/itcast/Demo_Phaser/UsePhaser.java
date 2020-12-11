package com.itcast.Demo_Phaser;

import java.util.concurrent.Phaser;

public class UsePhaser {
    public static void main(String[] args) {
        DnfPhaser phaser = new DnfPhaser();
        Thread[] threads = new Thread[4];

        int[] capty = {9999,9999,9999,1000000};

        phaser.bulkRegister(threads.length);
        // 指定参加phaser的线程数

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Player("P"+i,capty[i],phaser);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
