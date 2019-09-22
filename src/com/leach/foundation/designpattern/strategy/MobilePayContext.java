package com.leach.foundation.designpattern.strategy;

/**
 * @author Administrator
 * @name 概述：策略上下文，通过策略上下文来执行具体策略实现类的方法
 * @date 2019/5/13 22:05
 */
public class MobilePayContext {

    private MobilePay mobilePay;

    public MobilePayContext(MobilePay mobilePay) {
        this.mobilePay = mobilePay;
    }

    public void performPay(double amount) {
        this.mobilePay.pay(amount);
    }

    public MobilePay getMobilePay() {
        return mobilePay;
    }

    public void setMobilePay(MobilePay mobilePay) {
        this.mobilePay = mobilePay;
    }
}
