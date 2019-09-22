package com.leach.foundation.designpattern.strategy;

/**
 * @author Administrator
 * @name 概述：测试
 * @date 2019/5/13 22:09
 */
public class StrategyTest{

    public static void main(String[] args){
        MobilePay alipay = new AliMobilePay();
        MobilePay wxpay = new WxinMobilePay();

        MobilePayContext ctx1 = new MobilePayContext(alipay);
        MobilePayContext ctx2 = new MobilePayContext(wxpay);
        ctx1.performPay(500);
        ctx2.performPay(300);

    }
}
