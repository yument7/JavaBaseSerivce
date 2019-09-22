package com.leach.advance.thread.basis.communication;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/23 10:52
 */
public class ThreadChatTest{
    public static void main(String[] args){
        Depot mDepot = new Depot(100);
        Producer mPro = new Producer(mDepot);
        Consumer mCus = new Consumer(mDepot);

        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(60);
        mPro.produce(120);
        mPro.produce(110);
        mPro.produce(220);
    }
}
