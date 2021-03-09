package com.algrothm.exercise.tree;

import com.algrothm.exercise.search.Pair;
import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.TestCase;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeTraversal {
    // 算法1：递归访问左子树、右子树
    // 算法2：栈（模拟递归）
    // 算法3：morris
    //      如果节点无左子树，cur = cur.right
    //      如果节点有左子树，找到左子树的最右节点 --> 左子树的最后一个节点
    //          如果最右节点的right指向null，将最右节点的right指向当前节点，cur = cur.left --> 开始遍历cur的左子树
    //          如果最右节点的right指向cur，将最右节点的right指向null，cur = cur.right --> 说明cur的左子树已经完全遍历过了


    // 前序遍历-递归
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            result.add(root.val);
            result.addAll(preorderTraversal(root.left));
            result.addAll(preorderTraversal(root.right));
        }

        return result;
    }

    // 前序遍历-栈
    public static List<Integer> preorderTraversalStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur == null) {
                continue;
            }
            result.add(cur.val);
            stack.push(cur.right);
            stack.push(cur.left);
        }

        return result;
    }

    // 前序遍历-morris算法
    public static List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                // 找到左子树的最右节点
                TreeNode mostright = cur.left;
                while (mostright.right != null && mostright.right != cur) {
                    mostright = mostright.right;
                }

                if (mostright.right == null) {
                    result.add(cur.val);
                    mostright.right = cur;
                    cur = cur.left;
                } else {
                    mostright.right = null;
                    cur = cur.right;
                }
            }
        }

        return result;
    }

    // 中序遍历-递归
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            result.addAll(inorderTraversal(root.left));
            result.add(root.val);
            result.addAll(inorderTraversal(root.right));
        }

        return result;
    }

    // 中序遍历-栈
    public static List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }

        return result;
    }

    // 中序遍历-morris算法
    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode mostright = cur.left;
                while (mostright.right != null && mostright.right != cur) {
                    mostright = mostright.right;
                }

                if (mostright.right == null) {
                    mostright.right = cur;
                    cur = cur.left;
                } else {
                    result.add(cur.val);
                    mostright.right = null;
                    cur = cur.right;
                }
            }
        }

        return result;
    }

    // 后序遍历-递归
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            result.addAll(postorderTraversal(root.left));
            result.addAll(postorderTraversal(root.right));
            result.add(root.val);
        }

        return result;
    }

    // 后序遍历-栈
    public static List<Integer> postorderTraversalStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        // 记录上一个访问节点，如果上一个访问节点==cur.right，即右子树遍历完毕
        do {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode preNode = null;
            while (!stack.isEmpty()) {
                cur = stack.pop();
                if (cur.right == preNode) {
                    result.add(cur.val);
                    preNode = cur;
                } else {
                    stack.push(cur);
                    cur = cur.right;
                    break;
                }
            }
        } while ((!stack.isEmpty()));

        return result;
    }

    // 后序遍历-morris算法
    public static List<Integer> postorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode mostright = cur.left;
                while (mostright.right != null && mostright.right != cur) {
                    mostright = mostright.right;
                }

                if (mostright.right == null) {
                    mostright.right = cur;
                    cur = cur.left;
                } else {
                    // 节点的左子树已遍历完成，倒序遍历左子树的右边节点
                    mostright.right = null;
                    TreeNode newHead = reverse(cur.left);
                    TreeNode temp = newHead;
                    // 遍历
                    while (temp != null) {
                        result.add(temp.val);
                        temp = temp.right;
                    }
                    reverse(newHead);
                    cur = cur.right;
                }
            }
        }

        // 遍历整棵树的右边节点
        TreeNode newHead = reverse(root);
        TreeNode temp = newHead;
        // 遍历
        while (temp != null) {
            result.add(temp.val);
            temp = temp.right;
        }
        reverse(newHead);

        return result;
    }

    private static TreeNode reverse(TreeNode head) {
        TreeNode pre = null;
        TreeNode cur = null;
        TreeNode next = head;

        while (next != null) {
            cur = next;
            next = cur.right;
            cur.right = pre;
            pre = cur;
        }

        return cur;
    }


    // 层次遍历-广度优先搜索-递归
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrder(root, 0, result);
        return result;
    }

    public static void levelOrder(TreeNode root, Integer level, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        for (int i = result.size(); i <= level; i++) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        levelOrder(root.left, level+1, result);
        levelOrder(root.right, level+1, result);
    }

    // 层次遍历-非递归
    public static List<List<Integer>> levelOrderNorRecusive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> curLevel = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();
        if (root == null) {
            return result;
        }

        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            List<Integer> curLevelResult = new ArrayList<>();

            while (!curLevel.isEmpty()) {
                TreeNode curNode = curLevel.poll();
                curLevelResult.add(curNode.val);
                if (curNode.left != null) {
                    nextLevel.add(curNode.left);
                }
                if (curNode.right != null) {
                    nextLevel.add(curNode.right);
                }
            }

            result.add(curLevelResult);
            curLevel = nextLevel;
            nextLevel = new LinkedList<>();
        }

        return result;
    }

    static class BinaryTestCase implements TestCase<List> {

        TreeNode testRoot;
        String traversalType;
        List<Integer> testPreorderResult;
        List<Integer> testInorderResult;
        List<Integer> testPostorderResult;
        List<List<Integer>> testLevelorderResult;

        BinaryTestCase(String testTree, List<Integer> testPreorderResult, List<Integer> testInorderResult, List<Integer> testPostorderResult, List<List<Integer>> testLevelorderResult) {
            this.testRoot = TreeNode.buildBinaryTree(testTree);
            this.testPreorderResult = testPreorderResult;
            this.testInorderResult = testInorderResult;
            this.testPostorderResult = testPostorderResult;
            this.testLevelorderResult = testLevelorderResult;
        }

        @Override
        public List getExceptedResult() {
            switch (traversalType) {
                case "preorder":
                    return testPreorderResult;
                case "inorder":
                    return testInorderResult;
                case "postorder":
                    return testPostorderResult;
                default:
                    return testLevelorderResult;
            }
        }

    }

    public static void main(String[] args) {
        List<BinaryTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<BinaryTestCase>()
                .addCase(new BinaryTestCase("1,2,3,4,5,6,7", List.of(1, 2, 4, 5, 3, 6, 7), List.of(4, 2, 5, 1, 6, 3, 7), List.of(4, 5, 2, 6, 7, 3, 1), List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7))))
                .addCase(new BinaryTestCase("1,2,null,null,3,4", List.of(1, 2, 3, 4), List.of(2, 4, 3, 1), List.of(4, 3, 2, 1), List.of(List.of(1), List.of(2), List.of(3), List.of(4))))
                .addCase(new BinaryTestCase("1,null,2,3", List.of(1, 2, 3), List.of(1, 3, 2), List.of(3, 2, 1), List.of(List.of(1), List.of(2), List.of(3))))
                .build();

        // 前序遍历测试
        ExecTestCases.exec(testCases, binaryTestCase -> {
            binaryTestCase.traversalType = "levelorder";
            return levelOrderNorRecusive(binaryTestCase.testRoot);
        }, new ExecTestCases.ListComparator());
    }
}
