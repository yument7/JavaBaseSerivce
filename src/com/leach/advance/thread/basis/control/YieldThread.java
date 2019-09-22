package com.leach.advance.thread.basis.control;

/**
 * @author Administrator
 * @name 概述：
 *       yield方法是一个本地静态方法，yield方法可以让当前正在执行的线程暂停，但是不会阻塞该线程，它只是将线程转入就绪状态，让系统的线程调度器重新调度一次。
 *       当某个线程调用了yield方法后，只有优先级与当前线程相同或者优先级比当前线程更高的就绪状态的线程才会获得执行机会。
 * @date 2019/3/12 20:17
 */
public class YieldThread extends Thread{

    public YieldThread(){
        this.setName("YieldThd");
    }

    @Override
    public void run(){
        System.out.println("yieldStateO:"+this.getState().name());
        for(int i=0; i<10; i++){
            if(i==6){
                Thread.yield(); // 线程让步
                System.out.println(Thread.currentThread().getName()+":"+i+"\tstate:"+this.getState().name());
            }
        }
    }
}
