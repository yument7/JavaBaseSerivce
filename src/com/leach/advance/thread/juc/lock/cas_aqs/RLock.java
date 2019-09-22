package com.leach.advance.thread.juc.lock.cas_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Administrator
 * @name 概述：自己实现一把锁
 *          首先锁需要实现Lock接口， 然后需要持有一个私有的继承了AQS的内部类，根据需要选择重写对应的方法
 *
 * @date 2019/9/13 15:04
 */
public class RLock implements Lock {

    private Helper helper = new Helper();


    private class Helper extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            if(state == 0){
                // 利用cas原理修改state
                if (compareAndSetState(0, arg)) {
                    // 设置当前线程占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }else if(getExclusiveOwnerThread() == Thread.currentThread()){ // 可重入性
                setState(getState() + arg);
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            boolean flag = false;
            // 判断释放后是否为0
            if(state == 0){
                setExclusiveOwnerThread(null);
                return true;
            }
            setState(state);      // 此处是线程安全的

            return false;
        }

        public Condition newConditionObject(){
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        helper.acquire(1);
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
