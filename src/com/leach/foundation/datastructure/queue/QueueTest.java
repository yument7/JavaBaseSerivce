package com.leach.foundation.datastructure.queue;

/**
 * @author Administrator
 * @name 概述：
 * @date 2019/6/27 21:16
 */
public class QueueTest {
    public static void main(String[] args) {
        //testLineQueue();
        testLinkedQueue();
    }

    /**
     * 测试顺序结构队列
     */
    public static void testLineQueue() {
        LaLinearQueue<Integer> laLinearQueue = new LaLinearQueue<>();
        laLinearQueue.offer(1);
        laLinearQueue.offer(2);
        laLinearQueue.offer(3);
        System.out.println(laLinearQueue.poll());
        laLinearQueue.offer(4);

        System.out.println(laLinearQueue.size());
        System.out.println(laLinearQueue.poll());

        laLinearQueue.add(6);
        laLinearQueue.add(7);
        laLinearQueue.add(8);

        System.out.println(laLinearQueue.size());
    }

    /**
     * 测试链式结构队列
     */
    public static void testLinkedQueue() {
        LaLinkedQueue<String> linkedQueue = new LaLinkedQueue<>();

        boolean b1 = linkedQueue.add("java");
        boolean b2 = linkedQueue.offer("python");
        int s1 = linkedQueue.size();
        System.out.println("b1=" + b1 + "\nb2=" + b2 + "\ns1=" + s1);

        linkedQueue.add("c");
        linkedQueue.offer("c++");
        linkedQueue.offer("nodejs");

        String p = linkedQueue.poll();
        String h = linkedQueue.peek();
        String t = linkedQueue.rear();
        boolean empty = linkedQueue.isEmpty();
        int s2 = linkedQueue.size();
        System.out.println("p=" + p + "\nh=" + h + "\nt=" + t + "\nempty=" + empty + "\ns2=" + s2);
    }
}
