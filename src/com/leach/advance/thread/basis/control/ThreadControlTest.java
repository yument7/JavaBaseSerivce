package com.leach.advance.thread.basis.control;

/**
 * @author Administrator
 * @name 概述：sleep , yield , join 测试
 * @date 2019/3/12 20:22
 */
public class ThreadControlTest {
    public static void main(String[] args) throws InterruptedException{
        SleepThread sleepThread = new SleepThread();
        YieldThread yieldThread = new YieldThread();
        JoinThread joinThread = new JoinThread(sleepThread, yieldThread);
        DaemonThread daemonThread = new DaemonThread(joinThread);

        // 调用线程的start()方法，让线程从new状态 变到 runnable 可执行状态
        sleepThread.start();
        yieldThread.start();
        joinThread.start();
        daemonThread.start();

        testInterruptThread();

    }

    public static void testInterruptThread() throws InterruptedException {
        InterruptThread interruptThread = new InterruptThread();

        interruptThread.start();
        Thread.sleep(2000);
        interruptThread.interrupt();

    }

}
