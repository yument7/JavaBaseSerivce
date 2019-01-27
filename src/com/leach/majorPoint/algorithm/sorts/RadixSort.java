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
 * (3) 用一个数组统计原数组中某一位(从低位向高位统计)相同的数据出现的次数；
 * (4) 用一个数组计算原数组中某一位(从最低位向最高位计算)相同数据出现的位置；
 * (5) 将桶中数据从小到大用tmp数组收集起来；
 * (6) 重复(3)(4)(5)直到所有位都被统计并计算过，用tmp收集起来；
 * (7) 将tmp数组拷回到原数组中；
 * 时间复杂度
 * @date 2019/1/13 1:38
 */
public class RadixSort implements BaseSort{

    @Override
    public int[] sort(int[] array){
        return radixSort(array);
    }

    public static int[] radixSort(int[] array){
        long start = System.nanoTime();
        array = radixSortMain(array);
        long end = System.nanoTime();
        long costMills = end - start;
        System.out.println("RadixSort completed time:" + costMills + "ns");
        return array;
    }

    private static int[] radixSortMain(int[] array){
        if(array.length <= 1){
            return array;
        }
        // 寻找数组中的最大值，以便确定位数
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            max = array[i] > max ? array[i] : max;
        }

        // 计算最大数为几位数
        int digit = 0;
        while(max > 0){
            max /= 10;
            digit++;
        }

        // 临时数组用于拷贝 array
        int[] temp = new int[array.length];

        for(int i = 0; i < digit; i++){
            // 复制array 到 temp
            System.arraycopy(array, 0, temp, 0, array.length);

            int[] buckets = new int[10];

            int rate = (int) Math.pow(10, i);

            // 统计array中元素 digit 位上0-9出现的次数
            for(int j = 0; j < array.length; j++){
                int subKey = (array[j] / rate) % 10;
                buckets[subKey]++;
            }

            // 计算 array中各元素在该轮排序中的正确位置，比如 1 2 3 ，3前面有两个数，那么他就应该在第三个位置
            for(int j = 1; j < buckets.length; j++){
                buckets[j] = buckets[j] + buckets[j - 1];
            }

            /**
             * 进行排序，将原始数组元素装入到对应位置上
             * 注意：
             *      此处排序只能按从高索引到低索引遍历，因为从低位到高位排，第一次排序是按个位排，排完之后，十位上数字相同的数组成的序列
             *      已经是从小到大的顺序了，在第二轮十位排的时候，十位上数字相同的数个位大的必须排在后面，由于buckets的值是累加的，
             *      所以在--后，索引都会变小，自然小的数就在索引小的位置
             */
            for(int k = temp.length - 1; k >= 0; k--){
                int subKey = (temp[k] / rate) % 10;
                array[--buckets[subKey]] = temp[k];
            }
        }

        return array;
    }

    public static void main(String[] args){
        SortUtils.singleSortTest(10, new RadixSort());
    }
}
