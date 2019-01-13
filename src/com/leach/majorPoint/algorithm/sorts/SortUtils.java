package com.leach.majorPoint.algorithm.sorts;


public class SortUtils{
    // 输出array
    public static void printArray(int[] array){
        int len = array.length;
        for(int i = 0; i < len; i++){
            System.out.print(array[i] + "\t");
        }
    }

    // 计算时间差 纳秒计
    public static long getTimeInterval(long start, long end){
        long result = end - start;
        System.out.println("spend time:" + result);
        return result;
    }

    // 0，1 随机数产生
    public static int generateRandom(){
        return (int) Math.round(Math.random());
    }

    // 测试某种排序方法
    public static void singleSortTest(int size, BaseSort sortObject){
        if(size <= 1){
            System.out.println("out of bounds, please check the parameter of size");
            return;
        }

        int[] array = new int[size];
        for(int i = 0; i < array.length; i++){
            //强制类型转换运算符（） 优先级高于 +-*/运算符
            array[i] = (int) (Math.random() * size);
        }

        SortUtils.printArray(array);

        array = sortObject.sort(array);

        SortUtils.printArray(array);
    }
}
