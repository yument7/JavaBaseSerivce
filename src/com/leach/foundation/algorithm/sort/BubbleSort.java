package com.leach.foundation.algorithm.sort;

/**
 * @author Administrator
 * @name 冒泡排序类（稳定排序）
 * 概述：
 * 时间复杂度： O(n²)
 * @date 2019/1/13 1:32
 */
public class BubbleSort implements BaseSort {

    @Override
    public int[] sort(int[] array) {
        return bubbleSort(array);
    }

    // 冒泡排序方法
    public static int[] bubbleSort(int[] array) {
        long start = System.nanoTime();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        long end = System.nanoTime();
        long costMills = end - start;
        System.out.println("BubbleSort completed time:" + costMills + "ns");
        return array;
    }

    public static void main(String[] args) {
        SortUtils.singleSortTest(10, new BubbleSort());
    }
}
