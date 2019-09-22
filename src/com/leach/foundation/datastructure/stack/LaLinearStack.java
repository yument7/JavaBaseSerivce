package com.leach.foundation.datastructure.stack;

/**
 * @author Administrator
 * @name 概述：栈的简单线性实现
 * 栈是一种后进先出（LIFO）的表
 * 1. 初始化
 * new LaLinearStack()
 * <p>
 * 2. 成员
 * 数组：Object[] DATA
 * 初始容量： INITIAL_CAPACITY
 * 最大容量：MAX_CAPACITY
 * 栈顶元素： Object top
 * <p>
 * 3. 操作
 * 入栈-添加元素： push(Object o)
 * 出栈-删除元素： pop()
 * 查看栈顶元素： peek()
 * 判断栈空：isEmpty()
 * 判断栈满：isFull()
 * 清空栈：clear()
 * 栈大小：size()
 * @date 2019/6/24 20:29
 */
public class LaLinearStack<E> {

    private Object[] data;

    private final static int INITIAL_CAPACITY = 16;

    private final static int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    private Object top;

    private int index = 0;

    public LaLinearStack() {
        data = new Object[INITIAL_CAPACITY];
    }

    public LaLinearStack(int capacity) {
        if (capacity > MAX_CAPACITY || capacity < 0) {
            throw new IllegalArgumentException("the argument of capacity out of the legal value.");
        } else if (capacity <= INITIAL_CAPACITY) {
            data = new Object[INITIAL_CAPACITY];
        } else {
            data = new Object[capacity];
        }
    }

    /* 返回栈深度 */
    public int size() {
        return data.length;
    }

    /* 判断栈大小 */
    public boolean isFull() {
        return data[data.length - 1] != null;
    }

    /* 扩容 */
    private void resize() {
        int newCapacity = data.length + (INITIAL_CAPACITY >> 1);
        if (newCapacity <= MAX_CAPACITY) {
            Object[] tempData = new Object[newCapacity];
            System.arraycopy(data, 0, tempData, 0, data.length);
            data = tempData;
        } else {
            throw new IndexOutOfBoundsException("the new capacity out of the max value.");
        }
    }

    /* 入栈 */
    public void push(E e) {
        // 先判断栈是否已满, 已满需要扩容
        if (isFull()) {
            resize();
        }

        // 添加数据
        if (e != null) {
            top = e;
            data[index++] = top;
        }
    }

    /* 判断为空 */
    public boolean isEmpty() {
        return top == null;
    }

    /* 删除数据 */
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E temp = (E) data[--index];
        top = data[--index];
        index++;
        return temp;
    }

    /* 返回栈顶元素 */
    public E peek() {
        return (E) top;
    }

    /* 清空栈 */
    public void clear() {
        data = new Object[data.length];
        top = null;
    }
}
