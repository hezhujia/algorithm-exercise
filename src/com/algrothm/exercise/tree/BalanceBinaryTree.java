package com.algrothm.exercise.tree;

// 平衡二叉搜索树
public class BalanceBinaryTree {

    BTreeNode root;

    public void insert(int val) {
        if (root == null) {
            root = new BTreeNode(val);
            return;
        }
        BTreeNode curNode = root;
        while (curNode.left != null && curNode.right != null) {
            if (curNode.val >= val) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
    }

    public boolean contains(int val) {
        BTreeNode curNode = root;
        while (curNode != null) {
            if (curNode.val == val) {
                return true;
            }
            if (curNode.val < val) {
                curNode = curNode.right;
            }
            if (curNode.val > val) {
                curNode = curNode.left;
            }
        }
        return false;
    }

    public BTreeNode findMax() {
        BTreeNode curNode = root;
        while (curNode != null && curNode.right != null) {
            curNode = curNode.right;
        }
        return curNode;
    }

    public BTreeNode findMin() {
        BTreeNode curNode = root;
        while (curNode != null && curNode.left != null) {
            curNode = curNode.left;
        }
        return curNode;
    }

    private BTreeNode LL(BTreeNode treeNode) {
        BTreeNode newMid = treeNode.left;
        treeNode.left = newMid.right;
        newMid.right = treeNode;


        return newMid;
    }

    private BTreeNode RR(BTreeNode treeNode) {
        BTreeNode newMid = treeNode.right;
        treeNode.right = newMid.left;
        newMid.left = treeNode;
        return newMid;
    }

    private BTreeNode LR(BTreeNode treeNode) {
        // 第一步
        BTreeNode k2 = treeNode.left;
        BTreeNode k3 = k2.right;
        k2.right = k3.left;
        k3.left = k2;

        // 第二步
        treeNode.left = k3.right;
        k3.right = treeNode;

        return k3;
    }

    private BTreeNode RL(BTreeNode treeNode) {
        // 第一步
        BTreeNode k2 = treeNode.right;
        BTreeNode k3 = k2.left;
        k2.left = k3.right;
        k3.right = k2;

        // 第二步
        treeNode.right = k3.left;
        k3.left = treeNode;

        return k3;
    }

    private int getHeight(BTreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else {
            return treeNode.height;
        }
    }

    class BTreeNode {
        int height;
        int val;
        BTreeNode left;
        BTreeNode right;

        BTreeNode() {}
        BTreeNode(int val) { this.val = val; }
        BTreeNode(int val, BTreeNode left, BTreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
