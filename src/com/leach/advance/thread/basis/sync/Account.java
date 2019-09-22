package com.leach.advance.thread.basis.sync;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/20 21:44
 */
public class Account{

    // 账户名
    private String name;

    // 账户余额
    private double balance;

    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    // 取
    public synchronized void drawCash(double aimCash){
        if(aimCash > balance){
            System.out.println("账户余额不足");
            return;
        }
        balance -= aimCash;
        System.out.println("取钱金额："+aimCash+",账户余额："+balance);
    }

    // 存
    public synchronized void depositCash(double aimCash){
        balance += aimCash;
        System.out.println("存钱金额："+aimCash+",账户余额："+balance);
    }

    /** ---------------------getter setter----------------------- **/
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
}
