package com.leach.advance.annotation.meta.documented;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/6 14:24
 */

@MyDocumented
public class DocumentedTest {
    @Override
    @MyDocumented
    public String toString() {
        return this.toString();
    }
}