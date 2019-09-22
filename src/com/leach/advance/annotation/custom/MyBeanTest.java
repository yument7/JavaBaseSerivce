package com.leach.advance.annotation.custom;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/7 19:25
 */
@MyBean("hello annotation")
public class MyBeanTest{
    public static void main(String[] args){
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Class<MyBeanTest> test = MyBeanTest.class;
        MyBean bean = test.getAnnotation(MyBean.class);
        System.out.println(bean.value());
    }
}
