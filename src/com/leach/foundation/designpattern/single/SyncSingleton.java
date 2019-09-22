package com.leach.foundation.designpattern.single;

/**
 * @author Administrator
 * @name 概述：
 * 使用synchronized 使懒汉模式 线程安全
 * @date 2019/4/23 19:11
 */
public class SyncSingleton {

    private static SyncSingleton instance;

    private SyncSingleton() {
        // 防止通过反射实例化
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static synchronized SyncSingleton getInstance() {
        if (instance == null) {
            instance = new SyncSingleton();
        }
        return instance;
    }
}
