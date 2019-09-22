package com.leach.advance.thread.basis.control;

/**
 * @author Administrator
 * @name 概述：
 *       join方法为非静态方法，在A线程中调用B线程的join()方法会让A线程阻塞直到B线程运行完成。
 * @date 2019/3/12 20:17
 */
public class JoinThread extends Thread{

    private SleepThread sleepThread;

    private YieldThread yieldThread;

    public JoinThread(SleepThread sleepThread,YieldThread yieldThread){
        this.setName("JoinThread");

        this.sleepThread = sleepThread;
        this.yieldThread = yieldThread;
    }

    @Override
    public void run(){
        String sleepState = sleepThread.getState().name();
        String yieldState = yieldThread.getState().name();
        System.out.println("sleepState:"+sleepState+",\tyieldState:"+yieldState);
        for(int i=0;i<20;i++){
            if(i==10){
                try{
                    // 暂停当前线程，直到sleepTHread线程运行完成。
                    sleepThread.join();
                    yieldThread.join();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        sleepState = sleepThread.getState().name();
        yieldState = yieldThread.getState().name();
        System.out.println("sleepState:"+sleepState+",\tyieldState:"+yieldState);

        System.out.println(this.getName());
    }
}
