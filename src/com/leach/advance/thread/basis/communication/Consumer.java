package com.leach.advance.thread.basis.communication;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/23 10:50
 */
public class Consumer{

    private Depot depot;

    public Consumer(Depot depot){
        this.depot = depot;
    }

    public void consume(int goal){
        new Thread(){
            @Override
            public void run(){
                super.run();
                depot.consume(goal);
            }
        }.start();
    }
}
