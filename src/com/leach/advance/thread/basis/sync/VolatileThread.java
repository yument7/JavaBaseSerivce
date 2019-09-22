package com.leach.advance.thread.basis.sync;

/**
 * @author Administrator
 * @name 概述：Volatile关键字的使用：
 *            Volatile修饰的变量对多线程是可见的，即当多个线程访问volitale修饰的变量时，
 *            一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
 *            Volatile关键字有两个作用：
 *            1.保证不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的
 *            2.禁止进行指令重排序
 * @date 2019/3/21 21:17
 */
public class VolatileThread extends Thread{

    //private volatile boolean target = true;
    private boolean target = true;

    public void setTarget(boolean target){
        this.target = target;
    }

    @Override
    public void run(){
        System.out.println("the volatile thread is start run ...");
        int i = 0;
        while(target){
            i ++;
            //System.out.println(" the number of loop :i =" + i);
        }
        System.out.println("it's over!");
    }

    // 主线程
    public static void main(String[] args) throws InterruptedException{
        VolatileThread vt  = new VolatileThread();
        vt.start();
        Thread.sleep(500);
        vt.setTarget(false);
    }
}
