package com.leach.majorPoint.algorithm.sorts;

/**
 * @author Administrator
 * @name 归并排序（稳定排序）
 * 概述：
 * 归并排序算法采用先分后治的思想，将数组先采用二分法递归分成最小单元，然后排序每个单元。
 * 时间复杂度： o(NlogN)
 * @date 2019/1/12 20:27
 */
public class MergeSort implements BaseSort{

    @Override
    public int[] sort(int[] array){
        return mergeSort(array);
    }

    /**
     * 暴露的对外方法
     */
    public static int[] mergeSort(int[] array){
        int random = (int) Math.round(Math.random());
        long start = 0;
        long end = 0;
        switch(random){
            case 0:
                System.out.println("索引方式：");
                start = System.nanoTime();
                array = mergeSort1(array, 0, array.length - 1);
                end = System.nanoTime();
                break;
            case 1:
                System.out.println("切割数组方式：");
                start = System.nanoTime();
                array = mergeSort2(array);
                end = System.nanoTime();
                break;
        }
        long costMills = end - start;
        System.out.println("\nMergeSort completed time:" + costMills + "ns");
        return array;
    }

    /**
     *  采用数组索引的形式
     */
    private static int[] mergeSort1(int[] array, int low, int high){
        int mid = (low + high) / 2;
        if(low < high){
            mergeSort1(array, low, mid);
            mergeSort1(array, mid + 1, high);
            array = merge(array, low, mid, high);
        }
        return array;
    }

    /**
     *  采用直接分数组的方式进行
     */
    private static int[] mergeSort2(int[] array){
        if(array.length <= 1){
            return array;
        }
        int llen = array.length/2;
        int rlen = array.length - array.length/2;
        int[] left = new int[llen];
        int[] right = new int[rlen];
        for(int i = 0;i<llen;i++){
            left[i] = array[i];
        }
        for(int j = 0;j<rlen;j++){
            right[j] = array[j+llen];
        }

        left = mergeSort2(left);
        right = mergeSort2(right);

        array = merge(left,right);
        /*System.out.print("\n");
        for(int i = 0; i < array.length; i++)
            System.out.print(array[i] + "\t");*/
        return array;
    }

    private static int[] merge(int[] array, int low, int mid, int high){
        int[] target = new int[array.length];
        int i = low, j = mid + 1, k = low;
        int type = (int) Math.round(Math.random());
        switch(type){
            case 0:
                for(; i <= mid && j <= high; ){
                    if(array[i] > array[j]){
                        target[k++] = array[j++];
                    }else{
                        target[k++] = array[i++];
                    }
                }
                for(; i <= mid; i++){
                    target[k++] = array[i];
                }
                for(; j <= high; j++){
                    target[k++] = array[j];
                }
                System.out.print("\nstep:\t");
                for(i = low; i < k; i++){
                    array[i] = target[i];
                    System.out.print(array[i] + "\t");
                }
                break;
            case 1:
                while(i <= mid && j <= high){
                    if(array[i] > array[j]){
                        target[k++] = array[j++];
                    }else{
                        target[k++] = array[i++];
                    }
                }
                while(i <= mid){
                    target[k++] = array[i++];
                }
                while(j <= high){
                    target[k++] = array[j++];
                }
                i = low;
                while(i <= high){
                    array[i] = target[i];
                    i++;
                }

                break;
            default:
                System.out.println("beyond the range of type!");
        }

        return array;
    }


    private static int[] merge(int[] left, int[] right){
        int[] target = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        int type = (int) Math.round(Math.random()); // 随机选择type
        switch(type){
            case 0:
                for(; i <= left.length - 1 && j <= right.length - 1; ){
                    if(left[i] > right[j]){
                        target[k++] = right[j++];
                    }else{
                        target[k++] = left[i++];
                    }
                }

                for(; i <= left.length - 1; i++){
                    target[k++] = left[i];
                }

                for(; j <= right.length - 1; j++){
                    target[k++] = right[j];
                }

                break;
            case 1:
                while(i <= left.length - 1 && j <= right.length - 1){
                    if(left[i] > right[j]){
                        target[k++] = right[j++];
                    }else{
                        target[k++] = left[i++];
                    }
                }
                while(i <= left.length - 1){
                    target[k++] = left[i];
                    i++;
                }
                while(j <= right.length - 1){
                    target[k++] = right[j];
                    j++;
                }
                break;
            default:
                System.out.println("beyond the range of type!");
        }

        return target;
    }

    // 测试main方法
    public static void main(String[] args){
        SortUtils.singleSortTest(10,new MergeSort());
    }
}
