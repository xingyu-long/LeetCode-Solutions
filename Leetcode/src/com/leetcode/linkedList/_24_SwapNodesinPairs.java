package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _24_SwapNodesinPairs {

    /**
     * 24.Swap Nodes in Pairs
     * When: 2019/05/10
     *
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * For example,
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     *
     *
     * @param head
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode first;
        while (pre.next != null && pre.next.next != null) {
            first = pre.next;
            pre.next = first.next;
            first.next = first.next.next;
            pre.next.next = first;
            pre = first;
        }
        return dummy.next;
    }
}