package com.leach.foundation.datastructure.table;

/**
 * @author Administrator
 * @name 概述：线性表的顺序存储结构实现
 * 属性：
 * elements: 存储元素的数组
 * DEFAULT_CAPACITY: 初始默认容量
 * MAX_CAPACITY: 最大容量
 * size: 已占用大小
 * <p>
 * 方法：
 * 添加元素： add(E e)
 * 设置元素： set(int index, E e)
 * 删除元素： remove(int index)
 * 查询元素： get(int index)
 * 包含元素： contain(E e)
 * 返回元素位置：indexOf(E e)
 * 是否为空： isEmpty()
 * 清空表：   clear()
 * 表的大小： size()
 * @date 2019/7/17 19:56
 */
public class LaLinearTable<E> {

    private E[] elements;
    private final static int DEFAULT_CAPACITY = 16;
    private final static int MAX_CAPACITY = 1 << 10;
    private int size;

    public LaLinearTable() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public LaLinearTable(int capacity) {
        if (capacity < 0 || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException();
        }
        elements = (E[]) new Object[capacity];
    }

    /**
     * 添加元素
     * @param e
     */
    public void add(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    // 容量保证
    private void ensureCapacity() {
        int len = elements.length;
        if (size >= MAX_CAPACITY) {
            throw new IndexOutOfBoundsException("the size out of the max capacity.");
        }
        if (size >= len) {
            resize();
        }
    }

    // 扩容
    private void resize() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity << 1;
        newCapacity = newCapacity > MAX_CAPACITY ? MAX_CAPACITY : newCapacity;

        E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, oldCapacity);
        elements = newElements;
    }

    /**
     * 设置索引上的元素
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("the argument out of the limits of the table.");
        }
        elements[index] = e;
    }

    /**
     * 获取指定索引上的元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("the argument out of the limits of the table.");
        }
        return elements[index];
    }

    /**
     * 删除指定索引上的元素
     * @param index
     * @return
     */
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("the argument out of the limits of the table.");
        }

        E result = elements[index];

        if (index == size - 1) {
            elements[index] = null;
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - 1);
            elements[size - 1] = null;
        }
        size--;
        return result;
    }

    /**
     * 判断表中是否包含元素e
     * @param e
     * @return
     */
    public boolean contain(E e) {
        return indexOf(e) > -1;
    }

    /**
     * 检索指定参数e所在元素位置，如果没有返回-1
     * @param e
     * @return
     */
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断表是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清理表
     */
    public void clear() {
        elements = null;
    }

    /**
     * 表大小
     * @return
     */
    public int size() {
        return size;
    }
}
