package com.leach.majorPoint.dataStructure.tables;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Administrator
 * @name 概述（表数据结构实现思路）--线性表：
 * 1.由于数组为静态数组，所以需要初始容量，与扩容方法；
 * 2.提供多样化的初始化构造方法；
 * 3.表的功能：
 * 添加数据 add
 * 修改数据 set
 * 删除数据 remove
 * 查询数据 get
 * 清空表   clear
 * 4.表的属性：
 * 序列化id  serialVersionUID
 * 大小      size
 * 初始容量  DEFAULT_CAPACITY
 * 最大容量  MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8
 * 修改次数  modCount
 * 5.一些判断与查看：
 * 查看集合大小： size()
 * 判断集合为空： isEmpty()
 * 清空：        clear()
 *
 * @date 2019/1/20 12:59
 */
public class MiArrayList<E> implements Serializable{

    private static final Long serialVersionUID = 1234L;

    private static final int DEFAULT_CAPACITY = 10;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULT_EMPTY_ELEMENTDATA = {};

    private transient Object[] elementData;

    private int size;

    // 初始化
    public MiArrayList(){
        this.elementData = DEFAULT_EMPTY_ELEMENTDATA;
    }

    public MiArrayList(int initialCapacity){
        if(initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    // 容量保证
    private void ensureCapacity(int minCapacity){
        minCapacity = minCapacity <= DEFAULT_CAPACITY ? DEFAULT_CAPACITY : minCapacity;
        if(minCapacity - elementData.length > 0){
            grow(minCapacity);
        }

    }

    // 扩容
    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if(newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = Integer.MAX_VALUE;

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    // 索引边界保护
    private void rangeCheck(int index){
        if(index > size)
            throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);
    }

    // 添加元素
    public boolean add(E element){
        ensureCapacity(size + 1);
        elementData[size++] = element;
        return true;
    }

    // 添加集合元素
    public boolean addAll(Collection<? extends E> c){
        Object[] a = c.toArray();
        int incrementNum = a.length;
        ensureCapacity(size + incrementNum);
        System.arraycopy(a, 0, elementData, size, incrementNum);
        size += incrementNum;
        return incrementNum != 0;
    }
    // 删除值相等的元素
    public boolean remove(Object o){
        if(o == null){
            for(int index = 0; index < size; index++){
                if(elementData[index] == null){
                    int num = size - index - 1;
                    if(num > 0){
                        System.arraycopy(elementData, index + 1, elementData, index, num);
                    }
                    elementData[--size] = null;
                    return true;
                }
            }
        }else{
            for(int index = 0; index < size; index++){
                if(o.equals(elementData[index])){
                    int num = size - index - 1;
                    if(num > 0){
                        System.arraycopy(elementData, index + 1, elementData, index, num);
                    }
                    elementData[--size] = null;
                    return true;
                }
            }
        }
        return false;
    }
    // 删除指定位置上的元素
    public E remove(int index){
        rangeCheck(index);
        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;
        if(numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return oldValue;
    }

    // 修改
    public E set(int index, E element){
        rangeCheck(index);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    // 查询
    public E get(int index){
        rangeCheck(index);
        return (E) elementData[index];
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

        for(int index = 0; index < size; index++)
            elementData[index] = null;

        size = 0;
    }

}
