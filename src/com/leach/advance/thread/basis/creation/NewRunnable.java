package com.leach.advance.thread.basis.creation;

/**
 * @author Administrator
 * @name 概述：通过实现Runnable 接口方式创建线程
 * @date 2019/3/11 22:17
 */
public class NewRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
