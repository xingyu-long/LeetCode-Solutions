package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _143_ReorderList {


    /**
     *  143. Reorder List
     *  When:2019/7/8
     *  Difficulty: Medium

        Given a singly linked list L: L0→L1→…→Ln-1→Ln,
        reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
        You may not modify the values in the list's nodes, only nodes itself may be changed.

     * @param head
     */
    // time: O(n) space: O(1)
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head;
        ListNode l1 = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode l2 = reverse(slow.next);
        slow.next = null;
        merge(l1, l2);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    private void merge(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            ListNode temp = l1.next;
            l1.next = l2;
            l2 = l2.next;
            l1.next.next = temp;
            l1 = temp;
        }
    }
}
