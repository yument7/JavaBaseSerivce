package com.leach.foundation.designpattern.single;

/**
 * @author Administrator
 * @name 概述：
 * 懒汉模式，非线程安全
 * @date 2019/4/23 18:58
 */
public class LazySingleton {

    // 单例
    private static LazySingleton instance;

    // 构造方法私有
    private LazySingleton() {
    }

    // 获取单例实例方法
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
