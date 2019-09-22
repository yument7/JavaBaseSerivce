package com.leach.foundation.datastructure.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Administrator
 * @name 概述：哈夫曼树的实现
 *       哈夫曼树又叫最优二叉排序树， 重点是需要理解建树过程
 *       核心思想：每次取数值最小的两个节点，将之组成为一颗子树。
 *
 *       应用：
 *          加解密、数字通信，参数加密， 压缩功能等
 * @date 2019/9/17 19:46
 */
public class HuffmanTree<E> {

    private TreeNode<E> root;

    private ArrayList<TreeNode<E>> leafNodes = new ArrayList<>();

    public HuffmanTree(Map<E, Integer> weightMap) {
        setLeafNodes(weightMap);
    }

    /**
     * 根据传入的weightMap创建Huffman树
     */
    public void create() {
        if (leafNodes.isEmpty())
            throw new NullPointerException("leafNodes is null.");

        PriorityQueue<TreeNode<E>> queue = new PriorityQueue<>();
        // 将所有叶子节点加入优先队列
        for (int i = 0; i < leafNodes.size(); i++) {
            queue.offer(leafNodes.get(i));
        }

        // 根据叶子结点创建树
        int length = queue.size();
        TreeNode<E> newNode;
        for (int i = 0; i < length - 1; i++) {  // 有n个叶子结点，就需要构建n-1 次
            TreeNode<E> left = queue.poll();
            TreeNode<E> right = queue.poll();

            E key = (E) (left.key.toString() + right.key.toString());
            newNode = new TreeNode<>(key, left.weight + right.weight, left, right, null);

            left.parent = newNode;
            right.parent = newNode;

            queue.add(newNode); // 将新创建的node再加入优先队列排序
        }

        // 最后一次构建就是根节点
        root = queue.poll();

        // 为所有叶子节点构建编码
        generateCode();
    }

    /**
     * 打印编码信息
     */
    public void getCodeInfo() {
        for (TreeNode<E> leaf : leafNodes) {
            System.out.println(leaf.key + " : " + leaf.code);
        }
    }

    /**
     * 获取某个Key-weight的编码
     */
    public String getCode(E key) {

        for (TreeNode<E> leaf : leafNodes) {
            if (leaf.key.equals(key)) {
                return leaf.code;
            }
        }

        return null;
    }

    /**
     * 解码
     *
     * @return
     */
    public String decode(String code) {
        if (null == code || "".equals(code))
            throw new NullPointerException("code is null");

        Map<String, E> codeMap = new HashMap<>();
        for (TreeNode<E> leaf : leafNodes) {
            codeMap.put(leaf.code, leaf.key);
        }

        ArrayList<E> result = new ArrayList<>();
        String temp;
        E key;
        int i = 0;
        int k = 1;
        while (i < code.length() && k <= code.length()) {
            temp = code.substring(i, k);
            key = codeMap.get(temp);
            if (key == null) {
                k++;
            } else {
                result.add(key);
                i = k;
                k++;
            }
        }

        return result.toString();
    }


    /**
     * 为每个叶子节点产生对应编码
     */
    private void generateCode() {

        TreeNode<E> current;

        // 遍历所有叶子节点
        for (TreeNode<E> leaf : leafNodes) {
            current = leaf;

            String code = "";
            // 对每个叶子节点自下而上的找根节点路径
            while (current.parent != null) {
                if (current.parent.left == current) {
                    code = "0".concat(code);
                } else {
                    code = "1".concat(code);
                }

                // 找上一个父节点
                current = current.parent;
            }

            leaf.code = code; // 赋值编码
        }
    }

    /**
     * 根据weightMap创建叶子节点
     *
     * @param weightMap
     */
    private void setLeafNodes(Map<E, Integer> weightMap) {
        if (weightMap == null)
            throw new NullPointerException("weightMap is null.");

        // 根据weightMap 创建叶子结点
        for (E key : weightMap.keySet()) {
            leafNodes.add(new TreeNode<>(key, weightMap.get(key), null, null, null));
        }
    }

    /**
     * 树结点
     *
     * @param <E>
     */
    private static class TreeNode<E> implements Comparable<TreeNode<E>> {
        private E key;
        private int weight;         // 权重
        private TreeNode<E> left;   // 左子节点
        private TreeNode<E> right;  // 右子节点
        private TreeNode<E> parent; // 父节点

        private String code; // 对应编出的编码

        public TreeNode() {
        }

        public TreeNode(E e, int weight, TreeNode<E> left, TreeNode<E> right, TreeNode<E> parent) {
            this.key = e;
            this.weight = weight;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public int compareTo(TreeNode<E> o) {
            return this.weight - o.weight;
        }
    }
}
