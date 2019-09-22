package com.leach.advance.annotation.meta.inherited;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/6 15:54
 */

public class Cat extends Animal {
    @Override
    public void sayMyself() {
        System.out.println("大家好，我是猫咪^_^！");
    }
}
