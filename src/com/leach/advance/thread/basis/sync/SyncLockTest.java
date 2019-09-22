package com.leach.advance.thread.basis.sync;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/21 21:02
 */
public class SyncLockTest{

    public static void main(String[] args){
        Account account = new Account("jack", 5000.00);
        AccountUser user = new AccountUser(account);
        user.drawCash(3000.00);
        user.depositCash(2000.00);
        user.drawCash(4000.00);
        user.depositCash(1000.00);
        user.drawCash(2000.00);
        user.depositCash(1000.00);
    }
}
