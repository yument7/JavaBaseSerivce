package com.leach.majorPoint.algorithm.sorts;

/**
 * @author Administrator
 * @name 快速排序（不稳定排序）
 * 概述：
 *     快速排序是冒泡排序的改进,
 *     快速排序的思想是：选择一个基数作为标准，将大于基数的元素的放入基数的一边，将小于基数的元素放入另一边，然后
 *     将基数两边的数再进行上述的操作，直到两边的数的个数为1。
 *     实现方法：将用基数做为标准来分大于和小于的过程递归。
 * 时间复杂度：
 * @date 2019/1/13 1:32
 */
public class QuickSort implements BaseSort{

    @Override
    public int[] sort(int[] array){

        return quickSort(array);
    }

    /**
     * 对外提供访问的方法
     */
    public static int[] quickSort(int[] array){
        long start = System.nanoTime();
        array = quickSort(array,0,array.length-1);
        long end = System.nanoTime();
        long costMills = end - start;
        System.out.println("QuickSort completed time:" + costMills + "ns");
        return array;
    }

    /**
     * quickSort main process method
     */
    private static int[] quickSort(int[] array, int low, int high){

        if(low == high){
            return array;
        }

        int[] temp = new int[array.length];
        int baseKey = array[low];
        int l = low;
        int h = high;

        for(int i = low+1; i < high+1; i++){
            if(array[i] <= baseKey){
                temp[l] = array[i];
                l++;
            }else{
                temp[h] = array[i];
                h--;
            }
        }
        temp[l] = baseKey;
        for(int i=low;i<high+1;i++){
            array[i] = temp[i];
        }

        if(l > low) quickSort(array, low, l - 1);
        if(h < high) quickSort(array, h + 1, high);

        return array;
    }

    public static void main(String[] args){
        SortUtils.singleSortTest(10, new QuickSort());
    }

}
