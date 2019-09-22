package com.leach.advance.thread.basis.control;

/**
 * @author Administrator
 * @name 概述：
 *       sleep方法是一个本地静态方法，调用当前线程类的sleep方法会使当前线程从运行状态进入阻塞状态，在sleep时间段内，该线程不会获得执行的机会，
 *       即使系统中没有其他可运行的线程，处于sleep中的线程也不会运行，因此sleep方法常用来暂停程序的执行。sleep方法不会释放锁。
 *
 *       sleep 方法 需要抛出一个InterruptedException； 当线程在sleep()休眠时，如果被中断，这个异常就会产生;
 *       Thread.sleep()方法由于中断而抛出异常，此时，它会清除中断标记
 * @date 2019/3/12 20:18
 */
public class SleepThread extends Thread{

    public SleepThread(){
        this.setName("SleepThd");
    }

    @Override
    public void run(){
        System.out.println("sleepStateO:"+this.getState().name());
        for(int i=0; i<10; i++){
            if(i==5){
                try{
                    System.out.println("current-millis:"+System.currentTimeMillis());
                    Thread.sleep(5000); // 睡眠5s
                    System.out.println("current-millis:"+System.currentTimeMillis());
                    System.out.println(Thread.currentThread().getName()+":"+i+"\tstate:"+this.getState().name());
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
