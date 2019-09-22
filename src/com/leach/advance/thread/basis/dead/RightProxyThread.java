package com.leach.advance.thread.basis.dead;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/23 11:29
 */
public class RightProxyThread extends Thread{
    private Dielock dielock;

    public RightProxyThread(Dielock dielock){
        this.dielock = dielock;
    }

    @Override
    public void run(){
        try{
            System.out.println("right proxy doing ...");
            dielock.doneRight();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
