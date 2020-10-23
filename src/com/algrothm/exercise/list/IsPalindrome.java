package com.algrothm.exercise.list;

public class IsPalindrome {

    public static boolean isPalindrome(ListNode head) {
        // 思路，使用快慢指针
        // 慢指针对前半段链表进行reverse，当快指针指向null时结束
        // 比较reverse后的前半段链表及未reverse的后半段链表

        if (head == null || head.next == null) {
            return true;
        }

        ListNode fastPoint = head;
        ListNode slowPoint = head;
        ListNode preSlowPoint = head.next;

        // 奇数个节点，fastPoint.next = null
        // 偶数个节点，fastPoint.next.next = null
        while (true) {
            if (fastPoint.next == null) {
                slowPoint = slowPoint.next;
                break;
            }
            if (fastPoint.next.next == null) {
                break;
            }
            fastPoint = fastPoint.next.next;
            ListNode temp = preSlowPoint;
            preSlowPoint = preSlowPoint.next;
            temp.next = slowPoint;
            slowPoint = temp;
        }

        // 对比
        while (slowPoint != null && preSlowPoint != null) {
            if (slowPoint.val != preSlowPoint.val) {
                return false;
            }
            slowPoint = slowPoint.next;
            preSlowPoint = preSlowPoint.next;
        }

        return true;
    }

    // 测试用例
    // null
    // 一个节点
    // 偶数个节点
    // 奇数个节点

    public static void main(String[] args) {
        ListNode firstTest = ListNode.buildList(new int[]{});
        ListNode.printList(firstTest);
        System.out.println(isPalindrome(firstTest));
    }
}
