package com.leach.advance.thread.basis.communication;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/23 10:46
 */
public class Producer {

    private Depot depot;

    public Producer(Depot depot){
        this.depot = depot;
    }

    public void produce(int goal){
        new Thread(){
            @Override
            public void run(){
                super.run();
                depot.produce(goal);
            }
        }.start();
    }
}
