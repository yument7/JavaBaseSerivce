package com.leach.foundation.designpattern.strategy;

/**
 * @author Administrator
 * @name 概述：阿里支付--移动支付策略的具体实现策略类之一
 * @date 2019/5/13 22:03
 */
public class AliMobilePay implements MobilePay {
    @Override
    public void pay(double amount) {
        System.out.println("阿里支付金额：" + amount);
    }
}
