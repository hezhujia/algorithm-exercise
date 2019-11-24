public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode newListHead = new ListNode(-1);
        ListNode newListCurrentNode = newListHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                newListCurrentNode.next = l1;
                newListCurrentNode = newListCurrentNode.next;
                l1 = l1.next;
            } else {
                newListCurrentNode.next = l2;
                newListCurrentNode = newListCurrentNode.next;
                l2 = l2.next;
            }
        }

        newListCurrentNode.next = l1 != null ? l1 : l2;

        return newListHead.next;
    }
}
