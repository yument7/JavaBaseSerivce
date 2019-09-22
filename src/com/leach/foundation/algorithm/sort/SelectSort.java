package com.leach.foundation.algorithm.sort;

/**
 * @author Administrator
 * @name 简单选择排序（不稳定排序）
 * 概述：
 * 简单选择排序的思想是：每次从队列中选择出最大或最小元素的位置，每次选择完之后，让该位置元素与最前后或最后一个元素交换，然后依次类推。
 * 时间复杂度： O(n²)
 * @date 2019/1/13 1:32
 */
public class SelectSort implements BaseSort {

    @Override
    public int[] sort(int[] array) {
        return selectSort(array);
    }

    // 选择排序方法
    public static int[] selectSort(int[] array) {
        long start = System.nanoTime();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int target = i;
            for (int j = i + 1; j < len; j++) {
                if (array[target] > array[j]) {
                    target = j;
                }
            }
            int temp = array[i];
            array[i] = array[target];
            array[target] = temp;
        }
        long end = System.nanoTime();
        long costMills = end - start;
        System.out.println("SelectSort completed time:" + costMills + "ns");
        return array;
    }

    public static void main(String[] args) {
        SortUtils.singleSortTest(10, new SelectSort());
    }

}
