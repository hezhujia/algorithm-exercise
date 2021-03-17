package com.algrothm.exercise.tree;

import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.List;
import java.util.Stack;

public class BinaryTree {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    // 不占用额外空间 morris
    public static boolean isSameTreeMorris(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        while (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
            if (p.left == null && q.left == null) {
                p = p.right;
                q = q.right;
            } else if (p.left != null && q.left != null){
                TreeNode mostRightP = p.left;
                TreeNode mostRightQ = q.left;

                while (mostRightP.right != null && mostRightP.right != p && mostRightQ.right != null && mostRightQ.right != q) {
                    mostRightP = mostRightP.right;
                    mostRightQ = mostRightQ.right;
                }

                if (mostRightP.right == null && mostRightQ.right == null) {
                    mostRightP.right = p;
                    mostRightQ.right = q;

                    p = p.left;
                    q = q.left;
                } else if (mostRightP.right == p && mostRightQ.right == q) {
                    mostRightP.right = null;
                    mostRightQ.right = null;

                    p = p.right;
                    q = q.right;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return p == null && q == null;
    }

    // 是否是对称二叉树-递归法-两子树对称
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public static boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }

        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    // 非递归-使用morris
    public static boolean isSymmetricNoRecursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();

            if (node1 == null && node2 == null) {
                continue;
            } else if (node1 == null || node2 == null) {
                return false;
            } else if (node1.val != node2.val) {
                return false;
            }

            stack.push(node1.left);
            stack.push(node2.right);
            stack.push(node1.right);
            stack.push(node2.left);
        }

        return true;
    }

    static class BinaryTreeTestCase implements TestCase<Boolean> {

        TreeNode testP;
        TreeNode testQ;
        boolean exceptedResult;

        BinaryTreeTestCase(String testPStr, String testQStr) {
            this.testP = TreeNode.buildBinaryTree(testPStr);
            this.testQ = TreeNode.buildBinaryTree(testQStr);
            this.exceptedResult = testPStr.equals(testQStr);
        }

        BinaryTreeTestCase(String testPStr, boolean exceptedResult) {
            this.testP = TreeNode.buildBinaryTree(testPStr);
            this.exceptedResult = exceptedResult;
        }

        @Override
        public Boolean getExceptedResult() {
            return exceptedResult;
        }
    }

    public static void main(String[] args) {
        List<BinaryTreeTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<BinaryTreeTestCase>()
                .addCase(new BinaryTreeTestCase("1,2", false))
                .addCase(new BinaryTreeTestCase("1,2,3", false))
                .addCase(new BinaryTreeTestCase("1,2,2,3,4,4,3", true))
                .addCase(new BinaryTreeTestCase("1,2,2,null,3,null,3", false))
                .addCase(new BinaryTreeTestCase("null", true))
                .addCase(new BinaryTreeTestCase("2,3,3,4,5,null,4", false))
                .build();

        ExecTestCases.exec(testCases, binaryTreeTestCase -> isSymmetricNoRecursion(binaryTreeTestCase.testP));
    }
}
