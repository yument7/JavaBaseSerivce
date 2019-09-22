package com.leach.foundation.datastructure.tree;


import java.util.LinkedList;

/**
 * @author Administrator
 * @name 概述：二叉查找树（二叉排序树）的实现
 * @date 2019/7/18 20:42
 */
public class BSTree<E extends Comparable<? super E>> {

    private TreeNode<E> root;

    public BSTree() {
    }

    public BSTree(E e) {
        root = new TreeNode<>(e); // 初始化一个节点值为e的根节点
    }

    /**
     * 添加节点
     *
     * @param eTreeNode
     */
    public void insert(E e) {
        nullCheck(e);
        if (root == null) {
            this.root = new TreeNode<>(e, null, null, null);
            return;
        }

        insert(e, root);
    }

    // 插入元素的具体操作方法
    private void insert(E e, TreeNode<E> node) {
        int result = e.compareTo(node.value); // 比较插入的值与root节点的值
        if (result < 0) {
            if (node.left == null) {
                node.left = new TreeNode<>(e, node, null, null);
                return;
            }
            insert(e, node.left);
        } else {
            if (node.right == null) {
                node.right = new TreeNode<>(e, node, null, null);
                return;
            }
            insert(e, node.right);
        }
    }

    /**
     * 查找最大值
     *
     * @return
     */
    public E findMaxValue() {
        if (root == null)
            return null;

        return getMaxNode(root).value;
    }

    /**
     * 查找最小值
     *
     * @return
     */
    public E findMinValue() {
        if (root == null)
            return null;
        return getMinNode(root).value;
    }

    /**
     * 删除节点，分三种情况：
     * 1. 节点为叶子节点
     * 2. 节点只有一个子节点树
     * 3. 节点两个左右子节点树都存在
     * <p>
     * 步骤：
     * 1. 遍历整棵树，查找到对应的节点
     * 2. 分三种情况，进行对应操作
     */
    public void remove(E e) {
        TreeNode<E> target = getNode(e);

        // 排除空节点情况
        if (target == null)
            return;

        // 删除节点为叶子节点的情况
        if (target.left == null && target.right == null) {
            if (target.parent == null) {
                root = null;
                return;
            }

            if (target.parent.left == target)
                target.parent.left = null;

            if (target.parent.right == target)
                target.parent.right = null;

            target = null;
            return;
        }

        // 左子树不为空
        if (target.left != null && target.right == null) {
            if (target.parent == null) {
                target.left.parent = null;
                root = target.left;
                target = null;
                return;
            }

            if (target.parent.left == target) {
                target.parent.left = target.left;
                target.left.parent = target.parent;
                target = null;
                return;
            }

            if (target.parent.right == target) {
                target.parent.right = target.left;
                target.left.parent = target.parent;
                target = null;
                return;
            }
        }

        // 右子树不为空
        if (target.right != null && target.left == null) {
            if (target.parent == null) {
                target.right.parent = null;
                root = target.right;
                target = null;
                return;
            }

            if (target.parent.left == target) {
                target.parent.left = target.right;
                target.right.parent = target.parent;
                target = null;
                return;
            }

            if (target.parent.right == target) {
                target.parent.right = target.right;
                target.right.parent = target.parent;
                target = null;
                return;
            }
        }


        // 左右子树都不为空
        if (target.left != null && target.right != null) {
            // 目标节点为根节点
            if (target.parent == null) {
                // 根节点的右节点的左节点为空
                if (target.right.left == null) {
                    target.right.parent = null;
                    target.left.parent = target.right;
                    target.right.left = target.left;
                    root = target.right;
                    target = null;
                    return;
                }

                TreeNode<E> newRoot = getMinNode(target.right); // 不为空情况，找到根节点右边最小的节点，来做根节点
                newRoot.parent.left = null;
                newRoot.parent = null;
                target.left.parent = newRoot;
                target.right.parent = newRoot;
                newRoot.left = target.left;
                newRoot.right = target.right;
                root = newRoot;

                target = null;
                return;
            }

            // 不为根节点情况
            if (target.parent.left == target) {
                if (target.right.left == null) {
                    target.left.parent = target.right;
                    target.right.left = target.left;
                    target.parent.left = target.right;
                    target = null;
                    return;
                }

                TreeNode<E> newTarget = getMinNode(target.right);
                newTarget.parent.left = null;
                target.left.parent = newTarget;
                target.right.parent = newTarget;
                newTarget.left = target.left;
                newTarget.right = target.right;
                target.parent.left = newTarget;
                target = null;
                return;
            }

            if (target.parent.right == target) {
                if (target.right.left == null) {
                    target.left.parent = target.right;
                    target.right.left = target.left;
                    target.parent.right = target.right;
                    target = null;
                    return;
                }

                TreeNode<E> newTarget = getMinNode(target.right);
                newTarget.parent.left = null;
                target.left.parent = newTarget;
                target.right.parent = newTarget;
                newTarget.left = target.left;
                newTarget.right = target.right;
                target.parent.right = newTarget;
                target = null;
            }
        }
    }

    /**
     * 默认以循环方式获取节点
     */
    public TreeNode<E> getNode(E e) {
        return getNode(e, 0);
    }


    /**
     * 获取一个节点
     */
    private TreeNode<E> getNode(E e, int param) {
        if (param == 0)
            return getNodeByCycle(e);

        return getNodeByRecursion(e, root);
    }

    /**
     * 循环方式获取节点
     */
    private TreeNode<E> getNodeByCycle(E e) {
        if (e == null)
            throw new IllegalArgumentException();

        TreeNode<E> target = root;
        int result;

        while (target != null) {
            result = e.compareTo(target.value);

            if (result < 0) {
                target = target.left;
            } else if (result > 0) {
                target = target.right;
            } else {
                break;
            }
        }

        return target;
    }

    /**
     * 递归方式获取节点
     */
    private TreeNode<E> getNodeByRecursion(E e, TreeNode<E> node) {
        if (e == null)
            throw new IllegalArgumentException();

        TreeNode<E> target = node;
        int result = e.compareTo(target.value);
        if (result < 0) {
            target = getNodeByRecursion(e, target.left);
        } else if (result > 0) {
            target = getNodeByRecursion(e, target.right);
        }

        return target;
    }

    /**
     * 获取一颗树的最大值节点
     */
    private TreeNode<E> getMaxNode(TreeNode<E> node) {
        if (node == null)
            throw new IllegalArgumentException();

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    /**
     * 获取一棵树的最小值节点
     */
    private TreeNode<E> getMinNode(TreeNode<E> node) {
        if (node == null)
            throw new IllegalArgumentException();

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * 是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        nullCheck(e);
        return contains(e, root);
    }

    private boolean contains(E e, TreeNode<E> node) {
        if (node == null)
            return false;

        int compareResult = e.compareTo(node.value);
        if (compareResult < 0) {
            if (node.left == null) {
                return false;
            }
            return contains(e, node.left);
        } else if (compareResult > 0) {
            if (node.right == null) {
                return false;
            }
            return contains(e, node.right);
        } else {
            return true;
        }
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // 为null检查
    private void nullCheck(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
    }


    private void print(TreeNode<E> node) {
        System.out.print(node.value);
    }

    /* ===================二叉树的四种遍历方式===================== */
    // 先序遍历
    public void prevTraversal() {
        prevTraversal(root);
    }

    public void prevTraversal(TreeNode<E> root) {
        if (root == null)
            return;

        print(root);

        if (root.left != null)
            prevTraversal(root.left);

        if (root.right != null)
            prevTraversal(root.right);
    }

    // 中序遍历
    public void inTraversal() {
        inTraversal(root);
    }

    public void inTraversal(TreeNode<E> root) {
        if (root == null)
            return;

        if (root.left != null)
            inTraversal(root.left);

        print(root);

        if (root.right != null)
            inTraversal(root.right);
    }

    // 后序遍历
    public void postTraversal() {
        postTraversal(root);
    }

    public void postTraversal(TreeNode<E> root) {
        if (root == null)
            return;

        if (root.left != null)
            postTraversal(root.left);

        if (root.right != null)
            postTraversal(root.right);

        print(root);
    }

    // 广度优先遍历
    public void bfsTraversal() {
        bfsTraversal(root);
    }

    public void bfsTraversal(TreeNode<E> root) {
        if (root == null)
            return;

        LinkedList<TreeNode> container = new LinkedList<>();
        container.add(root);
        while (!container.isEmpty()) {
            // 将节点加入队列
            if (root.left != null)
                container.add(root.left);

            if (root.right != null)
                container.add(root.right);

            // 按层次顺序取出一个节点
            TreeNode node = container.poll();
            // 具体的操作
            print(node);
        }
    }

    /**
     * 树节点，三叉链表存储方式
     *
     * @param <E>
     */
    private static class TreeNode<E> {
        private E value;

        private TreeNode<E> parent;
        private TreeNode<E> left;
        private TreeNode<E> right;

        public TreeNode() {
        }

        public TreeNode(E e) {
            this.value = e;
        }

        public TreeNode(E e, TreeNode<E> parent, TreeNode<E> left, TreeNode<E> right) {
            this.value = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
