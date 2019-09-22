package com.leach.advance.thread.basis.creation;

import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/3/11 22:19
 */
public class ThreadTest {

    /* main 测试方法 */
    public static void main(String[] args) {
        testByExtendThread();
        testByImplementRunnable();
        testByImplementCallable();
        testThreadCommonProperty();
    }

    // 测试一：继承Thread方式
    public static void testByExtendThread() {
        Thread thread = new NewThread();
        thread.setName("Thread-thread-01");
        thread.start();
    }

    // 测试二：实现Runnable()接口方式
    public static void testByImplementRunnable() {
        Thread thread = new Thread(new NewRunnable());
        thread.setName("Thread-runnable-01");
        thread.start();
    }

    // 测试三：实现Callable接口方式
    public static void testByImplementCallable() {
        NewCallable callable = new NewCallable(1000);
        FutureTask task = new FutureTask<>(callable);
        Thread thread = new Thread(task, "Thread-callable-01");
        thread.start();
    }

    // 测试四：线程大部分属性
    public static void testThreadCommonProperty() {
        // 获取单前线程
        Thread thread = Thread.currentThread();
        // 获取线程名，可以调用setName() 方法自行设置
        String name = thread.getName();
        // 获取线程ID
        long id = thread.getId();
        // 线程优先级 最高10 默认5 最小1
        int priority = thread.getPriority();
        // 获取线程状态，状态是一个枚举类，只有6种状态：new runnable block waiting timed_waiting terminated
        String state = thread.getState().name();
        // 是否存活
        boolean isAlive = thread.isAlive();
        // 是否守护线程
        boolean isDeamon = thread.isDaemon();
        System.out.println(" id:" + id + "\n name:" + name + "\n priority:" + priority + "\n state:" + state + "\n isAlive:" + isAlive + "\n isDeamon:" + isDeamon);
    }
}
