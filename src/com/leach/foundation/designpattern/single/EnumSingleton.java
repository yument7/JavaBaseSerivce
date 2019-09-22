package com.leach.foundation.designpattern.single;

/**
 * @author Administrator
 * @name 概述：
 * 枚举类产生单例，线程安全，最方便简单高效
 * @date 2019/4/23 19:05
 */
public enum EnumSingleton {
    // 单例实例
    instance;

    // 构造函数,默认私有
    EnumSingleton() {
    }

    // 普通方法
    public void say() {
        System.out.println(instance.name());
    }

}
