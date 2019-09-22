package com.leach.advance.thread.basis.creation;

import java.util.concurrent.Callable;

/**
 * @author Administrator
 * @name 概述：通过实现Callable 接口方式创建可以返回结果的线程
 * @date 2019/3/11 22:18
 */
public class NewCallable implements Callable<Integer> {
    private long waitTime;

    public NewCallable(long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public Integer call() throws InterruptedException {
        Thread.sleep(waitTime);
        System.out.println(Thread.currentThread().getName());
        return 1;
    }
}
