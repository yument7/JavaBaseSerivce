package com.leach.advance.thread.basis.model;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/4/17 20:20
 */
public class ThreadLocalThread extends Thread{
    private Constant constant;

    public ThreadLocalThread(Constant constant){
        this.constant = constant;
    }

    @Override
    public void run(){
        constant.deposity(200.00);
    }
}
