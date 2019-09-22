package com.leach.foundation.datastructure.queue;

/**
 * @author Administrator
 * @name 概述：操作见LaLineQueue
 * @date 2019/6/27 21:31
 */
public class LaLinkedQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LaLinkedQueue() {

    }

    public LaLinkedQueue(E e) {
        head = tail = new Node<>(null, e, null);
        size++;
    }

    public boolean add(E e) {
        return offer(e);
    }

    /**
     * 入队操作 -> 添加元素，容量得到保证的前提下
     *
     * @param e
     * @return
     */
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("no permission null");
        }

        final Node<E> t = tail;
        final Node<E> newNode = new Node<>(t, e, null);

        if (t == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
        return true;
    }

    public E remove() {
        return poll();
    }

    /**
     * 弹出队首元素
     *
     * @return
     */
    public E poll() {
        if (head == null) {
            throw new NullPointerException("the queue has no elements.");
        }
        E result = head.value;
        head = head.next;
        size--;
        return result;
    }

    /**
     * 查看队首元素
     *
     * @return
     */
    public E peek() {
        if (head == null) {
            throw new NullPointerException("the queue has no elements.");
        }
        return head.value;
    }

    /**
     * 查看队尾元素
     *
     * @return
     */
    public E rear() {
        if (tail == null) {
            throw new NullPointerException("the queue has no elements.");
        }
        return tail.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 保存元素的结点类
     *
     * @param <E>
     */
    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node() {
        }

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

}
