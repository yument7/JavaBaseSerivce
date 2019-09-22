package com.leach.advance.thread.basis.creation;

/**
 * @author Administrator
 * @name 概述：通过继承Thread方式，创建线程
 * @date 2019/3/11 21:49
 */
public class NewThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }
}
