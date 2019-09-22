package com.leach.advance.annotation.meta.retention;

/**
 * @author Administrator
 * @name 概述：
 * 测试Retetion 元注解三种时期的异同，通过 javap -verbose RetentionTest 获取字节码文件观察异同
 * @date 2019/3/1 14:25
 */
public class RetentionTest {

    @SourcePolicy
    public void sourcePolicy() {
    }

    @ClassPolicy
    public void classPolicy() {
    }

    @RuntimePolicy
    public void runtimePolicy() {
    }
}
