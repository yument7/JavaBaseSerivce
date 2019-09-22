package com.leach.foundation.test;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/8/10 22:16
 */
public class BaseTest {
    public static void main(String[] args) {
        /*byte a = (byte) 0b10000000; // -0 原码
        byte b = (byte) 0b11111111; // -127 原码
        System.out.println(a + "," + b);*/

        int num = 50;
        num = num++*2;
        System.out.println(num);

    }
}
