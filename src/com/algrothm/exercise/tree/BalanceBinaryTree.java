package com.algrothm.exercise.tree;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    // 是否是平衡二叉树-节点的左右子树差不小于1
    // 递归算法
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return height(root) > 0;
    }

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        if (left < 0 || right < 0 || Math.abs(left-right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }

    static class BalanceBinaryTreeTestCase implements TestCase<Boolean> {

        TreeNode testNode;
        Boolean isBalance;

        BalanceBinaryTreeTestCase(String testTree, Boolean isBalance) {
            this.testNode = TreeNode.buildBinaryTree(testTree);
            this.isBalance = isBalance;
        }

        @Override
        public Boolean getExceptedResult() {
            return isBalance;
        }
    }

    public static void main(String[] args) {

        List<BalanceBinaryTreeTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<BalanceBinaryTreeTestCase>()
                .addCase(new BalanceBinaryTreeTestCase("1,2,2,3,3,null,null,4,4", false))
                .addCase(new BalanceBinaryTreeTestCase("3,9,20,null,null,15,7", true))
                .addCase(new BalanceBinaryTreeTestCase("1,2,2,3,null,null,3,4,null,null,4", false))
                .addCase(new BalanceBinaryTreeTestCase("1", true))
                .addCase(new BalanceBinaryTreeTestCase("1,2", true))
                .addCase(new BalanceBinaryTreeTestCase("1,2,null,3", false))
                .addCase(new BalanceBinaryTreeTestCase("1,null,2,null,3", false))
                .build();

        ExecTestCases.exec(testCases, testcase -> isBalanced(testcase.testNode));
    }
}
