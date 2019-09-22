package com.leach.foundation.datastructure.stack;

/**
 * @author Administrator
 * @name 概述：栈的链式存储结构简单实现
 * @date 2019/6/24 21:28
 */
public class LaLinkedStack<T> {

    // 结点元素
    private Node top;

    // 大小
    private int size;

    public LaLinkedStack() {
    }

    public LaLinkedStack(T e) {
        top = new Node(e, null);
        size++;
    }

    /* 入栈操作 */
    public void push(T e) {
        top = new Node(e, top);
        size++;
    }

    /* 查看栈顶元素 */
    public T peek() {
        return top.data;
    }

    /* 出栈操作 */
    public T pop() {
        T result = top.data;
        top = top.prev;
        size--;
        return result;
    }

    /* 栈大小 */
    public int size() {
        return size;
    }

    /* 是否空栈 */
    public boolean isEmpty() {
        return size == 0;
    }

    /* 清空栈 */
    public void clear() {
        top = null;
        size = 0;
    }

    // 内部类 结点对象, 链表结构的节点
    private class Node {

        private T data;

        private Node prev;

        Node() {
        }

        Node(T data, Node prev) {
            this.data = data;
            this.prev = prev;
        }
    }
}
