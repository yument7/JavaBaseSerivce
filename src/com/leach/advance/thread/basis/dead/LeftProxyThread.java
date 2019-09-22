package com.leach.advance.thread.basis.dead;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/23 11:29
 */
public class LeftProxyThread extends Thread{
    private Dielock dielock;

    public LeftProxyThread(Dielock dielock){
        this.dielock = dielock;
    }

    @Override
    public void run(){
        try{
            System.out.println("left proxy doing ...");
            dielock.doneLeft();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
