package com.leach.majorPoint.algorithm.sorts;

/**
 * @author Administrator
 * @name 堆排序（稳定排序）
 * 堆的概念：
 * 假设有n个数据元素的序列k0，k1，…，kn−1，当且仅当满足如下关系时，可以将这组数据称为小顶堆（小根堆）:
 * ki<=k2i+1且ki<=k2i+2（其中i=0, 2,…，(n−1)/2）
 * 或者，满足如下关系时，可以将这组数据称为大顶堆（大根堆）:
 * ki>=k2i+1且ki>=k2i+2（其中i=0, 2,…，(n−1)/2）
 * 概述：
 * 堆排序的步骤：1：建堆，2：交换根元素与尾元素，3：循环该过程
 * @date 2019/1/13 1:44
 */
public class HeapSort implements BaseSort{
    @Override
    public int[] sort(int[] array){
        return heapSort(array);
    }

    public static int[] heapSort(int[] array){
        long start = System.nanoTime();
        array = heapSortMain(array);
        long end = System.nanoTime();
        long costMills = end - start;
        System.out.println("BubbleSort completed time:" + costMills + "ns");
        return array;
    }

    private static int[] heapSortMain(int[] array){
        for(int i = array.length - 1; i > 0; i--){
            buildMaxHeap(array, i);
            swap(array, 0, i);
        }
        return array;
    }

    private static void buildMaxHeap(int[] array, int index){
        for(int i = (index - 1) / 2; i >= 0; i--){
            int k = index;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if(k < right && k == left){
                if(array[k] >= array[i]){
                    int temp = array[i];
                    array[i] = array[k];
                    array[k] = temp;
                }
                continue;
            }

            if(k == right){
                if(array[left] > array[i]){
                    int temp = array[i];
                    array[i] = array[left];
                    array[left] = temp;
                }

                if(array[right] > array[i]){
                    int temp = array[i];
                    array[i] = array[right];
                    array[right] = temp;
                }
                continue;
            }

            if(array[left] > array[i]){
                int temp = array[i];
                array[i] = array[left];
                array[left] = temp;
            }

            if(array[right] > array[i]){
                int temp = array[i];
                array[i] = array[right];
                array[right] = temp;
            }
        }
    }

    private static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args){
        SortUtils.singleSortTest(10,new HeapSort());
    }
}
