package com.algrothm.exercise.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public static String binaryTreeToString(TreeNode root) {
        if (root == null) {
            return "";
        }

        List<String> result = new ArrayList<>();
        LinkedList<TreeNode> searchList = new LinkedList<>();
        searchList.add(root);

        while (!searchList.isEmpty()) {
            int size = searchList.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = searchList.pollFirst();
                if (cur == null) {
                    result.add("null");
                } else {
                    result.add(String.valueOf(cur.val));
                    searchList.add(cur.left);
                    searchList.add(cur.right);
                }
            }
        }

        // 去掉后面的null
        int size = result.size();
        for (int i = size-1; i >= 0; i--) {
            if (result.get(i).equals("null")) {
                result.remove(i);
            } else {
                break;
            }
        }
        return String.join(",", result);
    }

    public static void main(String[] args) {
        System.out.println(binaryTreeToString(buildBinaryTree("3,1,4,null,null,2")));
    }
}
