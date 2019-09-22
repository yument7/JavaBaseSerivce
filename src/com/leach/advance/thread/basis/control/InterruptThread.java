package com.leach.advance.thread.basis.control;

/**
 * @author Administrator
 * @name 概述：线程中断
 * interrupt()     设置当前线程中断标记位
 * isInterupted()  判断是否中断
 * interupted()    判断是否中断，并清除当前中断状态
 * @date 2019/6/22 14:11
 */
public class InterruptThread extends Thread {

    public InterruptThread() {
        this.setName("Interrupt-Thread");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            if (i == 3) {
                boolean interrupted = this.isInterrupted();
                System.out.println("第" + i + "次：" + interrupted);
            }

            /*if (i == 8) {
                this.interrupt();
            }*/

            // 判断线程中断标记位，处理中断
            if(this.isInterrupted()){
                System.out.println("Interrupted!");
                break;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted when sleep.");
                Thread.currentThread().interrupt();
            }

            System.out.println("执行次数：" + i);

        }
    }
}
