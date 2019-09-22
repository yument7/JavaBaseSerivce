package com.leach.foundation.designpattern.single;

/**
 * @author Administrator
 * @name 概述：
 * 双重检查锁保证懒汉模式单例线程安全
 * @date 2019/4/23 19:21
 */
public class DoubleCheckSingleton {

    private static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
        // 防止反射调用产生实例
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }
    }

    private static DoubleCheckSingleton getInstance() {
        DoubleCheckSingleton result = instance;
        if (result == null) {
            synchronized (DoubleCheckSingleton.class) {
                result = instance;
                if (result == null) {
                    synchronized (DoubleCheckSingleton.class) {
                        instance = result = new DoubleCheckSingleton();
                    }
                }
            }
        }
        return result;
    }
}
