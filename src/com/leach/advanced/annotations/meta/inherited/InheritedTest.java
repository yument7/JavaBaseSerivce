package com.leach.advanced.annotations.meta.inherited;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/6 15:29
 */
public class InheritedTest{
    public static void main(String[] args){
        Class<Cat> child = Cat.class;
        MyInherited annotation = child.getAnnotation(MyInherited.class);
        System.out.println(annotation.name());
    }
}
