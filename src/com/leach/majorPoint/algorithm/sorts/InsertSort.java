package com.leach.majorPoint.algorithm.sorts;

/**
 * @author Administrator
 * @name 插入排序（稳定排序）
 * 概述：
 * 插入排序的模型从生活中的扑克牌或站队得到启示，以拿第一张牌开始，就认为是手里的牌是有序的，当拿到第二张牌时，开始与第一张牌比较，
 * 大就插在后面，当拿到第n张牌时，就和前面n-1张牌（n-1张牌已经有序），进行比较（按n--方式）。两层循环， 第一层（外层）从小到大的
 * 索引开始，选取key 牌，第二层（内层循环）从key牌索引开始向第一张牌开始，选择合适位置插入。
 * 时间复杂度： O(n²)
 * @date 2019/1/13 1:32
 */

public class InsertSort implements BaseSort{

    @Override
    public int[] sort(int[] array){
        return insertSort(array);
    }

    // type 表示循环方式， 1: for 循环， 0：while x循环
    public static int[] insertSort(int[] array){
        int type = SortUtils.generateRandom();
        long start = System.nanoTime();
        //从第二个数开始，因为默认第一张牌已经是有序的了
        for(int i = 1; i < array.length; i++){
            int target = array[i];

            switch(type){
                case 0:
                    // while 方式实现内层循环
                    int k = i;
                    while(k > 0 && target < array[k - 1]){
                        array[k] = array[k - 1];
                        k--;
                    }
                    array[k] = target;
                    break;
                case 1:
                    // for循环实现内层循环
                    int temp = i;
                    for(int j = i; j > 0 && target < array[j - 1]; j--, temp = j){
                        array[j] = array[j - 1];
                    }
                    array[temp] = target;
                    break;
                default:
                    System.out.println("beyond the range of type");
            }
        }
        long end = System.nanoTime();
        long costMills = end - start;
        System.out.println("\nInsertSort completed time:" + costMills + "ns");
        return array;
    }

    public static void main(String[] args){
        SortUtils.singleSortTest(10,new InsertSort());
    }
}
