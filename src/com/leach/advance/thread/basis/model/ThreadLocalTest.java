package com.leach.advance.thread.basis.model;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/4/17 20:30
 */
public class ThreadLocalTest{
    public static void main(String[] args){
        Constant constant = new Constant();
        ThreadLocalThread tlt = new ThreadLocalThread(constant);
        ThreadLocalThread tlt2 = new ThreadLocalThread(constant);
        tlt.start();
        tlt2.start();
    }
}
