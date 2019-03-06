package com.leach.majorPoint.dataStructure.tables;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Administrator
 * @name 概述（表数据结构实现思路）--链表：
 * 1.链表采用Node对象实现，所以需要在其中建内部类；
 * 2.Node 节点对象 的结构： 值， 前驱， 后驱；
 * 3.提供多样化的初始化构造方法；
 * 4.表的功能：
 * 添加数据 add
 * 修改数据 set
 * 删除数据 remove
 * 查询数据 get
 * 清空表   clear
 * 5.表的属性：
 * 序列化id  serialVersionUID
 * 大小      size
 * 5.一些判断与查看：
 * 查看集合大小： size()
 * 判断集合为空： isEmpty()
 * 清空：        clear()
 * @date 2019/1/20 12:59
 */
public class MiLinkedList<E> implements Serializable{

    private static final Long serialVersionUID = 1234L;

    private int size = 0;

    private Node<E> first;

    private Node<E> last;


    // 初始化
    public MiLinkedList(){

    }

    public MiLinkedList(E element){
        add(element);
    }

    // 添加元素
    public boolean add(E element){
        Node<E> newNode = new Node<>(last, element, null);
        if(first == null){  // 空链表， 新添加元素同时指向frist 和 last
            first = newNode;
            last = newNode;
        }else{
            last.next = newNode; // 让尾节点的next指向新增的节点
            last = newNode;  // 以新节点作为新的尾节点
        }
        size++;
        return true;
    }

    // 添加集合元素
    public boolean addAll(Collection<? extends E> c){
        Object[] newArray = c.toArray();
        int num = newArray.length;
        if(num > 0){
            for(int i = 0; i < num; i++){
                add((E) newArray[i]);
            }
        }else{
            return false;
        }
        size += num;
        return true;
    }

    // 删除值相等的元素
    public boolean remove(Object o){
        if(o == null){
            for(Node<E> x = first; x != null; x = x.next){
                if(x.item == null){

                }
            }

        }
        return false;
    }
    // 删除指定位置上的元素
    public E remove(int index){
        return null;
    }

    // 修改
    public E set(int index, E element){
        return null;
    }

    // 查询
    public E get(int index){
        return null;
    }

    // 获取长度
    public int size(){
        return size;
    }

    // 是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 清空
    public void clear(){

    }


    // Node 节点
    private static class Node<E>{
        E item;
        Node prev;
        Node next;

        Node(Node prev, E item, Node next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

}
