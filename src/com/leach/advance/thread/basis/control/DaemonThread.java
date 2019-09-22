package com.leach.advance.thread.basis.control;

/**
 * @author Administrator
 * @name 概述：
 *       有一种线程，它是在后台运行的，它的任务是为其他的线程提供服务，这种线程被称为“后台线程(Daemon Thread)，”又称“守护线程”;
 *       后台线程有个特征：如果前台线程都死亡，后台线程会自动死亡。
 * @date 2019/3/12 20:34
 */
public class DaemonThread extends Thread{

    private JoinThread joinThread;


    public DaemonThread(){
        this.setDaemon(true);
    }

    public DaemonThread(JoinThread joinThread){
        this.setDaemon(true);
        this.joinThread = joinThread;
    }

    @Override
    public void run(){
        System.out.println(joinThread.getName());
        try {
            // 若前台进程运行完毕，守护线程会自动退出，后面的代码不一定会运行
            Thread.sleep(7000);
            int count = 0;
            while(count <= 100){
                System.out.println("第"+count+"次："+joinThread.getName());
                count ++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
