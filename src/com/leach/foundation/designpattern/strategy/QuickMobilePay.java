package com.leach.foundation.designpattern.strategy;

/**
 * @author Administrator
 * @name 概述：快闪支付--移动支付策略的具体实现策略类之一
 * @date 2019/5/13 22:04
 */
public class QuickMobilePay implements MobilePay {
    @Override
    public void pay(double amount) {
        System.out.println("快闪支付金额：" + amount);
    }
}
