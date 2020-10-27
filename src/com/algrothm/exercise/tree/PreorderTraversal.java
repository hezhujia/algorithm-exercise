package com.algrothm.exercise.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal {

    // 递归解法
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            result.add(root.val);
            result.addAll(preorderTraversal(root.left));
            result.addAll(preorderTraversal(root.right));
        }

        return result;
    }

    // 迭代解法
    public List<Integer> preorderTraversalTwo(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode visitNode = stack.pollFirst();
            if (visitNode == null) {
                break;
            }
            result.add(visitNode.val);
            if (visitNode.right != null) {
                stack.addFirst(visitNode.right);
            }
            if (visitNode.left != null) {
                stack.addFirst(visitNode.left);
            }
        }

        return result;
    }
}
