package com.leach.advance.thread.basis.dead;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/23 11:33
 */
public class DiedLockTest{
    public static void main(String[] args){
        Dielock dielock = new Dielock();
        LeftProxyThread lpt = new LeftProxyThread(dielock);
        RightProxyThread rpt = new RightProxyThread(dielock);
        lpt.start();
        rpt.start();
    }
}
