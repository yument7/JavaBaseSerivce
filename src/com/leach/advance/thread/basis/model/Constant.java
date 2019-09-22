package com.leach.advance.thread.basis.model;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/4/17 19:40
 */
public class Constant{

    private double nbanlance = 200.00;

    private ThreadLocal<Double> tlbanlance = new ThreadLocal<Double>(){
        @Override
        protected Double initialValue(){
            return 200.00;
        }
    };

    public void deposity(double cash){
        this.nbanlance = this.nbanlance + cash;
        tlbanlance.set(tlbanlance.get()+cash);
        System.out.println("normal banlance:"+this.nbanlance+"\nThreadLocal banlance:"+tlbanlance.get());
    }
}
