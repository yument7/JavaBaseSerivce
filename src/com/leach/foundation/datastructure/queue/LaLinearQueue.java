package com.leach.foundation.datastructure.queue;

import java.util.NoSuchElementException;

/**
 * @author Administrator
 * @name 概述：队列的简单实现 Queue
 * 队列也是一种弱化的列表，通常但不一定都是先进先出(FIFO)，但是队列必须按一定的顺序排序元素，按照从一端进入从另一端出的规则。
 * 成员：
 * 队列默认容量： DEFAULT_CAPACITY
 * 队列最大容量： MAX_CAPACITY
 * <p>
 * 保存队列元素的数组: Object[] data
 * 头： head
 * 尾： tail
 * <p>
 * 操作：
 * 添加元素： boolean add(E e)
 * 插入元素： boolean offer(E e)
 * 查询队首元素： E element()
 * 查询队首元素:  E peek()
 * 查询队尾元素： E rear()
 *
 * 弹出元素： E poll()
 * 弹出元素： E remove()
 * <p>
 * 队列空判断： isEmpty()
 * 队列满判断： isFull()
 * 队列的大小： size()
 * 清空队列： clear()
 * @date 2019/6/26 19:11
 */
public class LaLinearQueue<E> {

    private final static int DEFAULT_CAPACITY = 4;

    private final static int MAX_CAPACITY = 2 << 10;

    private Object[] elements;

    private int head;

    private int tail;

    public LaLinearQueue() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public LaLinearQueue(int capacity) {
        if (capacity <= DEFAULT_CAPACITY) {
            elements = new Object[DEFAULT_CAPACITY];
        } else if (capacity > MAX_CAPACITY) {
            elements = new Object[MAX_CAPACITY];
        } else {
            int n = (int) Math.log(capacity);
            capacity = (int) Math.pow(2, n);
            // 保证队列的容量是2的n次方
            elements = new Object[capacity];
        }
    }

    /**
     * 入队操作 -> 添加元素, 容量超出限制抛出IllegalStateException异常
     *
     * @param e
     * @return
     */
    public boolean add(E e) {
        if (offer(e)) {
            return true;
        } else {
            throw new IllegalStateException("Capacity has reached its maximum limit.");
        }
    }

    /**
     * 入队操作 -> 添加元素，容量得到保证的前提下
     *
     * @param e
     * @return
     */
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }

        elements[tail] = e;

        /*
         * 当 y = 2^n - 1、0 < x < 2^n -1 时，y & x = x ；当 x = 2^n 时，y & x = 0；
         * 使用数组实现环形队列利用了这一点，优点：
         * 避免空间浪费（如果出队操作时，不移动头后面的元素，就会造成出队元素位置上空间浪费）
         * 节省运算时间（如果出队操作时，将出队的位置补齐就要将后面的元素向前面移动）
         * */
        if ((tail = (tail + 1) & (elements.length - 1)) == head) {
            int h = head;
            int l = elements.length;
            int d = l - h;
            int newCapacity = l << 1;
            if (newCapacity > MAX_CAPACITY) {
                throw new IllegalStateException("Capacity has reached its maximum limit.");
            }

            Object[] newData = new Object[newCapacity];
            // 环形队列，head不一定固定在0上，所以拷贝数组元素时需要两次
            System.arraycopy(elements, h, newData, 0, d);
            System.arraycopy(elements, 0, newData, d, h);
            elements = newData;
            head = 0;
            tail = l;
        }

        return true;
    }

    /**
     * 检索但不删除此队列的头部，如果此队列为空，则返回 null 。
     * @return
     */
    public E peek() {

        return (E) elements[head];
    }

    /**
     * 检索，但不删除，这个队列的头。 此方法与peek的不同之处在于，如果此队列为空，它将抛出异常。
     * @return
     */
    public E element() {
        E result = peek();
        if(result == null){
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * 查询队列尾节点数据
     * @return
     */
    public E rear(){
        return (E) elements[tail];
    }

    /**
     * 检索并删除此队列的头。 此方法与poll不同之处在于，如果此队列为空，它将抛出异常。
     * @return
     */
    public E remove() {
        E result = poll();
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * 检索并删除此队列的头，如果此队列为空，则返回 null 。
     * @return
     */
    public E poll() {
        int h = head;

        E result = (E) elements[h];
        if (result == null) {
            return null;
        }
        elements[h] = null;
        head = (h + 1) & (elements.length - 1);
        return result;
    }

    /**
     * 返回队列大小
     * @return
     */
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    /**
     * 检查队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return head == tail;
    }

    /**
     * 清空队列
     */
    public void clear(){
        int h = head;
        int t = tail;
        if (h != t) { // clear all cells
            head = tail = 0;
            int i = h;
            int mask = elements.length - 1;
            do {
                elements[i] = null;
                i = (i + 1) & mask;
            } while (i != t);
        }
    }
}
