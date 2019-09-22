package com.leach.foundation.datastructure.tree;

import java.util.LinkedList;

/**
 * @author Administrator
 * @name 概述：B树的实现，B树的重点在于分裂
 * @date 2019/9/17 19:22
 */
public class BTree<E extends Comparable<? super E>> {

    private int order; // 阶数

    private TreeNode<E> root;

    public BTree() {
        this.order = 6; // 默认值为6
        root = new TreeNode<>(order);
    }

    public BTree(int order) {
        this.order = order;
        root = new TreeNode<>(order);
    }


    /**
     * 插入数据
     */
    public void insert(E e) {
        // 找到需要放入的那颗树结点，找的结点都是叶子节点
        TreeNode<E> node = find(e);

        // 节点数据存放已满，需要进行分裂， 可能存在循环的分裂的情况
        if (node.count == order - 1) {
            split(node, e, null);
        } else {
            node.isLeafNode = true;
            // 节点放入数据
            if (node.count == 0) {
                node.keys[node.count++] = e;
                return;
            }

            int i = node.count;
            while (i > 0 && e.compareTo(node.keys[i - 1]) < 0) {
                node.keys[i] = node.keys[i - 1];
                i--;
            }
            node.keys[i] = e;
            node.count++;
        }
    }

    /**
     * 分裂过程，这是一个递归过程
     * 1. 首先将需要分裂的结点，分裂成两个，即new一个新的Node
     * 2. 判断目标结点是否叶子结点； true: 新结点只需设置keys； false: 需要设置childs
     * 3. 分裂出两个新结点后，然后需要分三种情况，去挂接到父节点
     * ①. 父节点为根节点
     * ②. 父节点不为根节点，且父节点没有放满
     * ③. 父节点不为根节点，且父节点已经放满
     */
    private void split(TreeNode<E> target, E e, TreeNode<E> node) {

        TreeNode<E> newChild = new TreeNode<>(order);   // 分出来的新子节点
        newChild.isLeafNode = target.isLeafNode;

        int postion = getPosition(target, e);           // 记录e在target中的位置
        E[] newKeys = BuildNewKeyArray(target, e);      // 将要插入的数与原来的数组排序成一个新的数组
        e = newKeys[order / 2];                         // 要添加到父节点中的key

        // 拷贝数据到新child中
        System.arraycopy(newKeys, order / 2 + 1, newChild.keys, 0, order / 2 - 1);
        newChild.count = order / 2 - 1;

        // 拷贝数据到target中
        System.arraycopy(newKeys, 0, target.keys, 0, order / 2);
        for (int j = order / 2; j < order - 1; j++) {
            target.keys[j] = null;
            target.count--;
        }

        if (!target.isLeafNode) {                       // 如果不是叶子结点，还要处理childs
            TreeNode<E> temp = target.childs[order - 1];

            // 位置的变换，在keys中重新排列了，所以childs的顺序也需要重新调整
            if (postion == order - 1) {
                temp = node;
            } else {
                int i = order - 1;
                while (i > postion) {
                    target.childs[i] = target.childs[i - 1];
                    i--;
                }
                node.parent = target;
                target.childs[postion + 1] = node;
            }

            // 调整后的childs复制到newchild的childs中去
            System.arraycopy(target.childs, order / 2 + 1, newChild.childs, 0, order / 2 - 1);
            newChild.childs[order / 2 - 1] = temp;

            // 重新调整newChild中childs中各节点的父节点，原先是指向target的
            for (int i = 0; i < newChild.count + 1; i++) {
                newChild.childs[i].parent = newChild;
            }

            // 清理target中多余的数据
            for (int i = order / 2; i < order - 1; i++) {
                target.keys[i] = null;
                target.childs[i + 1] = null;
            }
        }

        // 如果是根节点
        if (target.parent == null) {
            root = new TreeNode<>(order);
            root.isLeafNode = false;
            root.keys[0] = e;
            root.count++;
            root.childs[0] = target;
            root.childs[1] = newChild;
            target.parent = root;
            newChild.parent = root;
        } else {
            if (target.parent.count < order - 1) {
                TreeNode<E> parent = target.parent;
                newChild.parent = target.parent;

                // 找子节点在父节点中的位置
                int pos = getPosition(parent, e);

                int x = parent.count;
                while (pos < x) {
                    parent.keys[x] = parent.keys[x - 1];
                    parent.childs[x + 1] = parent.childs[x];
                    x--;
                }
                parent.keys[pos] = e;
                parent.childs[pos + 1] = newChild;
                parent.count++;
            } else {
                // 父节点已满的情况，需要再分裂
                split(target.parent, e, newChild);
            }
        }
    }

    /**
     * 找e应该插入的位置结点
     */
    private TreeNode find(E e) {
        TreeNode<E> target = root;
        int pos = getPosition(target, e);

        while (!target.isLeafNode) {
            target = target.childs[pos];
            pos = getPosition(target, e);
        }
        return target;
    }

    /**
     * 获取插入的值在目标节点的位置
     */
    private int getPosition(TreeNode<E> node, E e) {
        int k = node.count;
        while (k >= 1 && e.compareTo(node.keys[k - 1]) < 0) {
            k--;
        }
        return k;
    }

    /**
     * 重新构建一个keys数组
     */
    private E[] BuildNewKeyArray(TreeNode<E> target, E e) {
        E[] newkeys = (E[]) new Comparable[order];
        System.arraycopy(target.keys, 0, newkeys, 0, order - 1);
        int i = order - 1;
        while (i >= 1 && e.compareTo(newkeys[i - 1]) < 0) {
            newkeys[i] = newkeys[i - 1];
            i--;
        }
        newkeys[i] = e;
        return newkeys;
    }


    /**
     * 删除
     */
    public void remove(E e) {

    }

    /**
     * 是否包含
     * bfs遍历方式
     */
    public boolean contains(E e) {
        if (e == null)
            throw new IllegalArgumentException("not permission null");

        LinkedList<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode<E> target;
        while (!queue.isEmpty()) {
            target = queue.poll();

            for (int i = 0; i < target.count; i++) {
                if (e.compareTo(target.keys[i]) == 0) {
                    return true;
                }
            }

            if(!target.isLeafNode){
                for(int i=0; i <= target.count; i++){
                    queue.offer(target.childs[i]);
                }
            }
        }
        return false;
    }

    /**
     * 查询最大值
     */
    public E getMax() {
        TreeNode<E> target = findMaxNode();
        return target.keys[target.count - 1];
    }

    /**
     * 查找最小值
     */
    public E getMin() {
        return findMinNode().keys[0];
    }

    /**
     * 找存值的最大结点
     */
    private TreeNode<E> findMaxNode() {
        TreeNode<E> target = root;

        while (!target.isLeafNode) {
            target = target.childs[target.count];
        }

        return target;
    }

    /**
     * 找存值的最小结点
     *
     * @return
     */
    private TreeNode<E> findMinNode() {
        TreeNode<E> target = root;

        while (!target.isLeafNode) {
            target = target.childs[0];
        }

        return target;
    }

    /**
     * 中序遍历，顺序打印值
     */
    public void printByOrder() {
        printByOrder(root);
    }

    private void printByOrder(TreeNode<E> node) {
        if (node.isLeafNode) {
            for (int i = 0; i < node.count; i++) {
                System.out.print(node.keys[i] + " ");
            }
        } else {
            for (int i = 0; i <= node.count; i++) {
                if (node.childs[i] != null) {
                    printByOrder(node.childs[i]);
                }
                if (i < node.count) {
                    System.out.print(node.keys[i] + " ");
                }
            }
        }
    }

    /**
     * 遍历
     */
    public void print() {
        print(root);
    }

    private void print(TreeNode<E> node) {
        for (int i = 0; i < node.count; i++) {
            System.out.print(node.keys[i] + " ");
        }
        if (!node.isLeafNode) {
            for (int j = 0; j <= node.count; j++) {
                if (node.childs[j] != null) {
                    System.out.println();
                    print(node.childs[j]);
                }
            }
        }
    }


    /**
     * 树节点
     */
    private static class TreeNode<E> {
        private int count; // 节点值个数计数

        private E[] keys; // 存储数据 最多 order - 1 个
        private TreeNode[] childs; // 存储子节点， 最多 order 个
        private TreeNode<E> parent;
        private boolean isLeafNode; // 是否叶子节点

        public TreeNode() {
        }

        public TreeNode(int order) {
            this.keys = (E[]) new Comparable[order - 1];
            this.childs = new TreeNode[order];
            this.isLeafNode = true;
            count = 0;
        }
    }
}
