package com.leach.majorPoint.algorithm.sorts;

/**
 * @author Administrator
 * @name 基数排序
 * 概述：
 * 基数排序(Radix Sort)是桶排序的扩展，
 * 基本思想是：从低位开始将待排序的数按照这一位的值放到相应的编号为0~9的桶中。等到低位排完得到一个子序列，再将这个序列按照次低位
 * 的大小进入相应的桶中，一直排到最高位为止，数组排序完成。
 * 具体做法是：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，
 * (1) 遍历序列找出最大的数(为的是确定最大的数是几位数)；
 * (2) 开辟一个与数组大小相同的临时数组tmp；
 * (3) 用一个count数组统计原数组中某一位(从低位向高位统计)相同的数据出现的次数；
 * (4) 用一个start数组计算原数组中某一位(从最低位向最高位计算)相同数据出现的位置；
 * (5) 将桶中数据从小到大用tmp数组收集起来；
 * (6) 重复(3)(4)(5)直到所有位都被统计并计算过，用tmp收集起来；
 * (7) 将tmp数组拷回到原数组中；
 * 时间复杂度
 * @date 2019/1/13 1:38
 */
public class RadixSort implements BaseSort{

    @Override
    public int[] sort(int[] array){
        return new int[0];
    }

    public static int[] radixSort(int[] array){
        if(array.length <= 1){
            return array;
        }
        // 寻找数组中的最大值，以便确定位数
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            if(max < array[i]){
                max = array[i];
            }
        }

        int exp = max;

        return array;
    }

}
