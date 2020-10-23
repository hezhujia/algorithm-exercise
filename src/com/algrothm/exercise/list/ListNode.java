package com.algrothm.exercise.list;

public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode buildList(int[] vals) {
        ListNode head = new ListNode(0);
        ListNode curNode = head;
        for (int value : vals) {
            curNode.next = new ListNode(value);
            curNode = curNode.next;
        }
        return head.next;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (head != null) {
            stringBuilder.append(head.val)
                    .append("->");
            head = head.next;
        }
        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        ListNode head = buildList(new int[]{3, 2, 3, 4, 5});
        printList(head);
    }
}
