package com.leach.foundation.designpattern.single;

/**
 * @author Administrator
 * @name 概述：
 * 静态内部类实现懒加载模式单例
 * @date 2019/4/23 20:02
 */
public class InnerSingleton {

    private InnerSingleton() {
    }

    public static InnerSingleton getInstance() {
        return HelperHolder.instance;
    }

    // 提供懒加载模式单例实例
    private static class HelperHolder {
        private static final InnerSingleton instance = new InnerSingleton();
    }
}
