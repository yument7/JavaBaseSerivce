package com.leach.majorPoint.algorithm.sorts;

/**
 * @author Administrator
 * @name 概述：所有排序类测试
 * @date 2019/1/13 1:01
 */
public class SortsTest{
    public static void main(String[] args){
        int[] a = new int[1000];

        for(int i = 0; i < a.length; i++){
            //强制类型转换运算符（） 优先级高于 +-*/运算符
            a[i] = (int) (Math.random() * 1000);
        }

        InsertSort.insertSort(a);
        MergeSort.mergeSort(a);
        BubbleSort.bubbleSort(a);
        SelectSort.selectSort(a);
    }
}
