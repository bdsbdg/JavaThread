package com.itcast.Demo_Phaser;

import java.util.concurrent.Phaser;

/**
 *  下面说说Phaser的高级用法，在Phaser内有2个重要状态，分别是phase和party。
 *  phase就是阶段，初值为0，当所有的线程执行完本轮任务，同时开始下一轮任务时，
 *  意味着当前阶段已结束，进入到下一阶段，phase的值自动加1。party就是线程，
 *  party=4就意味着Phaser对象当前管理着4个线程。Phaser还有一个重要的方法经常需要被重载，
 *  那就是boolean onAdvance(int phase, int registeredParties)方法。此方法有2个作用：
 *  1、当每一个阶段执行完毕，此方法会被自动调用，因此，重载此方法写入的代码会在每个阶段执行完毕时执行，
 *  相当于CyclicBarrier的barrierAction。
 *  2、当此方法返回true时，意味着Phaser被终止，因此可以巧妙的设置此方法的返回值来终止所有线程。
 * @author liujun
 */

public class DnfPhaser extends Phaser {
    @Override
    public boolean onAdvance(int phase,int registeredParties){
        /**
         * 当每一个阶段线程到齐，此方法会被自动调用 当此方法返回true时，意味着Phaser被终止.
         *
         * phase: 就是阶段，初值为0，当所有的线程执行完本轮任务，同时开始下一轮任务时，
         *                      意味着当前阶段已结束，进入到下一阶段，phase的值自动加1.
         *
         * registeredParties 就是这个阶段有几个人参加
        * */
        switch (phase){
            case 0:
                System.out.println("=======黑雾之源over当前阶段人数："+registeredParties+"=======");
                return false;
            case 1:
                System.out.println("=======擎天之柱over当前阶段人数："+registeredParties+"=======");
                return false;
            case 2:
                System.out.println("=======黑色火山over当前阶段人数："+registeredParties+"=======");
                return false;
            case 3:
                System.out.println("=======安图恩的心脏over当前阶段人数："+registeredParties+"=======");
                return true;
            case 4:
                System.out.println("=======翻牌over当前阶段人数："+registeredParties+"=======");
                return true;
            default:
                return true;
        }
    }
}
