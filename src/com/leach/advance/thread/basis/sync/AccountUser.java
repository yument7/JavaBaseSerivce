package com.leach.advance.thread.basis.sync;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/21 20:32
 */
public class AccountUser{
    private Account account;

    public AccountUser(Account account){
        this.account = account;
    }

    public void drawCash(double aimCash){
        new Thread(){
            @Override
            public void run(){
                super.run();
               account.drawCash(aimCash);
            }
        }.start();
    }

    public void depositCash(double aimCash){
        new Thread(){
            @Override
            public void run(){
                super.run();
                account.depositCash(aimCash);
            }
        }.start();
    }
}
