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
        ListNode l1 = head;
        ListNode mid = findMid(head);
        ListNode l2 = reverse(mid.next);
        mid.next = null;
        // connect l1 and l2.
        while (l1 != null && l2 != null) {
            ListNode l1Next = l1.next;
            ListNode l2Next = l2.next;
            l1.next = l2;
            l2.next = l1Next;
            l1 = l1Next;
            l2 = l2Next;
        }
    }

    public ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {148
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}
