package com.leach.foundation.datastructure.stack;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/6/24 21:42
 */
public class StackTest {
    public static void main(String[] args) {
        //testlineStack();
        //testlinkStack();

        System.out.println((0-1) & 7);
    }

    /**
     * 测试 顺序存储结构 栈
     */
    private static void testlineStack() {
        LaLinearStack<String> lineStack = new LaLinearStack<>();
        System.out.println(lineStack.isEmpty());

        lineStack.push("hello");
        lineStack.push("world");
        lineStack.push("java");
        lineStack.push("python");
        lineStack.push("nodejs");
        lineStack.push("js");
        lineStack.push("linux");
        lineStack.push("c");
        lineStack.push("c++");
        lineStack.push("scala");
        lineStack.push("go");
        lineStack.push("ruby");
        lineStack.push("kotlin");
        lineStack.push("andriod");
        lineStack.push("shell");
        lineStack.push("css");

        System.out.println(lineStack.isFull());
        System.out.println(lineStack.size());
        System.out.println(lineStack.peek());

        lineStack.push("window");
        lineStack.push("andriod");
        System.out.println(lineStack.size());
        System.out.println(lineStack.peek());
        System.out.println(lineStack.pop());
        System.out.println(lineStack.peek());

        lineStack.clear();
        System.out.println(lineStack.isEmpty());
    }

    /**
     * 测试 链式存储结构 栈
     */
    private static void testlinkStack() {
        LaLinkedStack<String> linkStack = new LaLinkedStack<>();
        System.out.println(linkStack.isEmpty());

        linkStack.push("hello");
        linkStack.push("world");
        linkStack.push("java");
        linkStack.push("python");
        linkStack.push("nodejs");
        linkStack.push("js");

        System.out.println(linkStack.size());
        System.out.println(linkStack.peek());

        linkStack.push("window");
        linkStack.push("andriod");
        System.out.println(linkStack.size());
        System.out.println(linkStack.peek());
        System.out.println(linkStack.pop());
        System.out.println(linkStack.peek());

        linkStack.clear();
        System.out.println(linkStack.isEmpty());
    }
}
