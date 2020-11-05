package com.algrothm.exercise.tree;

public class SumNumbers {

    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumNumbers(root, 0);
    }

    private static int sumNumbers(TreeNode root, int preNum) {
        int result = 0;
        // 叶子节点
        if (root.left == null && root.right == null) {
            return preNum * 10 + root.val;
        }
        preNum = preNum * 10 + root.val;
        if (root.left != null) {
            result += sumNumbers(root.left, preNum);
        }
        if (root.right != null) {
            result += sumNumbers(root.right, preNum);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(sumNumbers(TreeNode.buildBinaryTree("4,9,0,5,1")));
    }

}
