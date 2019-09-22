package com.leach.foundation.datastructure.table;


/**
 * @author Administrator
 * @name 概述：
 * @date 2019/7/17 20:00
 */
public class TableTest {
    public static void main(String[] args) {
        //testLinearTable();
        testLinkedTable();
    }

    public static void testLinearTable(){
        LaLinearTable<String> linearTable = new LaLinearTable<>(4);
        linearTable.add("java");
        linearTable.add("python");
        linearTable.add("c");
        linearTable.add("c++");
        linearTable.set(3,"c#");
        linearTable.add("c++");
        linearTable.remove(1);
    }

    public static void testLinkedTable(){
        LaLinkedTable<String> linkedTable = new LaLinkedTable<>();
        linkedTable.add("java");
        linkedTable.add("js");
        linkedTable.add("c");
        linkedTable.add("c++");
        linkedTable.add("python");
        linkedTable.add("nodejs");
        String s1 = linkedTable.set(2, "go");
        String r1 = linkedTable.remove(4);
        String g1 = linkedTable.get(1);
    }
}
