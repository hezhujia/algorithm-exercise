package com.algrothm.exercise.tree;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode buildBinaryTree(final String tree) {
        String[] nodeVals = tree.replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");
        int[] nullCounts = new int[nodeVals.length];

        for (int i = 0; i < nodeVals.length; i++) {
            nullCounts[i] = nodeVals[i].equals("null") ? 1 : 0;
            if (i != 0) {
                nullCounts[i] += nullCounts[i - 1];
            }
        }

        return buildBinaryTree(0, nullCounts, nodeVals);
    }

    public static TreeNode buildBinaryTree(int i, final int[] nullCounts, final String[] nodeVals) {
        if (i >= nodeVals.length || nodeVals[i].equals("null")) {
            return null;
        }

        TreeNode curNode = new TreeNode(Integer.parseInt(nodeVals[i]));
        curNode.left = buildBinaryTree((i-nullCounts[i])*2+1, nullCounts, nodeVals);
        curNode.right = buildBinaryTree((i-nullCounts[i])*2+2, nullCounts, nodeVals);

        return curNode;
    }
}
