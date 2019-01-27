package com.leach.majorPoint.dataStructure.tables;

import java.io.Serializable;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/1/20 12:59
 */
public class MyArrayList<E> implements Serializable{

    private static final Long serialVersionUID = 1234L;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULT_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    // 初始化
    public MyArrayList(){
        this.elementData = DEFAULT_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int initialCapacity){
        if(initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    // 添加
    public void add(E element){
        if(this.size == 0){
            this.elementData = new Object[DEFAULT_CAPACITY];
        }

    }

    // 删除

    // 修改

    // 查询

    // 获取长度


}
