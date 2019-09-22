package com.leach.foundation.designpattern.single;

/**
 * @author Administrator
 * @name 概述：
 * 饿汉模式，线程安全
 * @date 2019/4/23 19:03
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
