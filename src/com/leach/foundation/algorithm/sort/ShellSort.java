package com.leach.foundation.algorithm.sort;

/**
 * @author Administrator
 * @name 希尔排序(不稳定排序)
 * 概述：
 * 希尔排序是插入排序的改进，基本思想是，选取一个步长来拆分序列，让每个步长间隔的元素行成新数组然后进行插入排序操作；
 * 该排序方式是不稳定的，排序的好坏取决于步长的选择，一般以数组长度的一办为初始步长。
 * 时间复杂度
 * @date 2019/1/13 1:38
 */
public class ShellSort implements BaseSort {

    @Override
    public int[] sort(int[] array) {
        return shellSort(array);
    }

    public static int[] shellSort(int[] array) {
        long start = System.nanoTime();
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int target = array[j];
                while (j >= gap && target < array[j - gap]) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = target;
            }
        }
        long end = System.nanoTime();
        long costMills = end - start;
        System.out.println("ShellSort completed time:" + costMills + "ns");
        return array;
    }

    public static void main(String[] args) {
        SortUtils.singleSortTest(10, new ShellSort());
    }
}




















