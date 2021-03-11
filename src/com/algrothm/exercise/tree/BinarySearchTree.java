package com.algrothm.exercise.tree;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.List;

// 每个节点的左子树小于该节点，右子树大于该节点
// 中序遍历结果是顺序的
public class BinarySearchTree {
    // 交换错误的两个节点的位置
    // morris遍历
    public static void recoverTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null;

        TreeNode swapNode1 = null;
        TreeNode swapNode2 = null;

        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && pre.val > cur.val) {
                    if (swapNode1 == null) swapNode1 = pre;
                    swapNode2 = cur;
                }
                pre = cur;
                cur = cur.right;
            } else {
                TreeNode mostright = cur.left;
                while (mostright.right != cur && mostright.right != null) {
                    mostright = mostright.right;
                }

                if (mostright.right == null) {
                    mostright.right = cur;
                    cur = cur.left;
                } else {
                    mostright.right = null;
                    if (pre != null && pre.val > cur.val) {
                        if (swapNode1 == null) swapNode1 = pre;
                        swapNode2 = cur;
                    }
                    pre = cur;
                    cur = cur.right;
                }
            }
        }

        swap(swapNode1, swapNode2);
    }

    private static void swap(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    static class BinarySearchTreeTestCase implements TestCase<String> {

        TreeNode testRoot;
        String testResultString;

        BinarySearchTreeTestCase(String testTree, String testResultString) {
            this.testRoot = TreeNode.buildBinaryTree(testTree);
            this.testResultString = testResultString;
        }

        @Override
        public String getExceptedResult() {
            return testResultString;
        }
    }

    public static void main(String[] args) {
        List<BinarySearchTreeTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<BinarySearchTreeTestCase>()
                .addCase(new BinarySearchTreeTestCase("1,3,null,null,2", "3,1,null,null,2"))
                .addCase(new BinarySearchTreeTestCase("3,1,4,null,null,2", "2,1,4,null,null,3"))
                .build();

        ExecTestCases.exec(testCases,testCase -> {
            recoverTree(testCase.testRoot);
            return TreeNode.binaryTreeToString(testCase.testRoot);
        });
    }
}
