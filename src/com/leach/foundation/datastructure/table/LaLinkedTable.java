package com.leach.foundation.datastructure.table;

/**
 * @author Administrator
 * @name 概述：表的链式结构实现·
 * @date 2019/7/17 19:56
 */
public class LaLinkedTable<E> {

    private Node<E> element;
    private int size;

    public LaLinkedTable() {
    }

    public LaLinkedTable(E e) {
        element = new Node<>(e, null);
        size++;
    }

    /**
     * 添加数据
     * @param e
     */
    public void add(E e) {
        Node<E> newNode = new Node<>(e, null);

        if (element == null) {
            element = newNode;
        } else {
            Node<E> prev = element;
            Node<E> next;
            do {
                next = prev.next;
                if (next == null) {
                    prev.next = newNode;
                }
                prev = next;
            } while (prev != null);
        }
        size++;
    }

    /**
     * 获取索引index 上的数据
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        Node<E> next = element;
        E result = null;
        int i = 0;
        while (next.next != null) {
            if (index == i) {
                result = next.value;
                break;
            }
            next = next.next;
            i++;
        }
        return result;
    }

    /**
     * 修改索引index上的值
     * @param index
     * @param e
     * @return
     */
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        Node<E> next = element;
        E oldVal = null;
        int i = 0;
        while (next.next != null) {
            if (index == i) {
                oldVal = next.value;
                next.value = e;
                break;
            }
            next = next.next;
            i++;
        }
        return oldVal;
    }

    /**
     * 删除索引index上的值
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        Node<E> prev = element;
        Node<E> next = prev;
        E oldVal = null;
        int i = 0;
        while (next.next != null) {
            if (index == i) {
                oldVal = next.value;
                prev.next = next.next;
                next = null;
                size--;
                break;
            }
            prev = next;
            next = next.next;
            i++;
        }
        return oldVal;
    }

    /**
     * 链表是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node<E> temp = element;
        boolean result = false;
        while (temp.next != null) {
            if (e == null && temp.value == null) {
                result = true;
                break;
            }
            if (temp.value != null && temp.value.equals(e)) {
                result = true;
                break;
            }
            temp = temp.next;
        }
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 链表的节点对象
     *
     * @param <E>
     */
    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E e, Node<E> next) {
            this.value = e;
            this.next = next;
        }
    }
}
