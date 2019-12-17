package com.leach.foundation.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/9/7 12:40
 */
public class TreeTest {

    public static void main(String[] args) {

       /* Map<String, Integer> source = new HashMap<>();
        source.put("i", 5);
        source.put("love", 2);
        source.put("you", 0);
        source.put("can", 1);
        source.put("hope", 9);
        source.put("understand", 8);
        source.put("very", 3);
        source.put("good", 7);

        HuffmanTree<String> tree = new HuffmanTree<>(source);

        tree.create();

        tree.getCodeInfo();

        System.out.println(tree.decode("11011111111100"));*/

       testBTree();

    }

    public static void testBTree(){
        BTree<Integer> testTree = new BTree<>(6);

        int[] arr = { 3,14,7,1,8,5,11,17,13,6,23,12,20,26,4,16,18,24,25,19,9,10,15,27,28,2, 43, 46,32,38, 51,67,41,33, 36,87} ;

        for (int i = 0; i < arr.length; i++) {
            testTree.insert(arr[i]);
            System.out.println(arr[i] + "插入后的Btree");
            testTree.print();
            System.out.println();
            System.out.println("----------");
        }

        System.out.println("length = "+ arr.length);
        System.out.println("================================");

        testTree.printByOrder();

        System.out.println("Max="+ testTree.getMax() + ", Min=" +testTree.getMin());

        System.out.println("contains 14:" + testTree.contains(14) + ", contains 90:" + testTree.contains(90));
    }



    public static void testBSTree(){
        BSTree<Integer> testTree = new BSTree<>();
        int[] array = {19, 20, 5, 16, 3, 7, 12, 17, 18};

        int[] array2 = {16, 10, 24, 27, 18, 6, 12, 3, 8, 11, 14};
        for(int i=0; i<array2.length; i++){
            testTree.insert(array2[i]);
        }

        testTree.remove(18);

        testTree.inTraversal();

        testTree.remove(16);

        testTree.inTraversal();
    }


}
