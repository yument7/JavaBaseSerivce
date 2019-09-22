package com.leach.advance.thread.basis.dead;

/**
 * @author Administrator
 * @name 概述：线程死锁简单例子 -- 锁顺序死锁
 *         死锁概念：线程死锁是因为多个线程相互等待对方持有的锁而产生的。
 *         死锁产生的条件：
 *              当前线程拥有其他线程需要的资源
 *              当前线程等待其他线程已拥有的资源
 *              都不放弃自己拥有的资源
 *
 *         避免死锁产生的方法：
 *              以固定的顺序加锁
 *              缩减同步代码块范围，最好仅操作共享变量时才加锁
 *              使用tryLock()定时锁，超过时限则返回错误信息
 *
 * @date 2019/3/23 11:20
 */
public class Dielock{

    private final Object lockOne = new Object();

    private final Object lockTwo = new Object();

    public void doneLeft () throws InterruptedException{
        synchronized(lockOne){
            Thread.sleep(500);
            System.out.println("我获得锁：lockOne");
            synchronized(lockTwo){
                Thread.sleep(500);
                System.out.println("我想获得锁：lockTwo");
            }
        }
    }

    public void doneRight() throws InterruptedException{
        synchronized(lockTwo){
            Thread.sleep(500);
            System.out.println("我获得锁：lockTwo");
            synchronized(lockOne){
                Thread.sleep(500);
                System.out.println("我想获得锁：lockOne");
            }
        }
    }
}
