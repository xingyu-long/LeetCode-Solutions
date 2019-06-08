package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _92_ReverseLinkedListII {

    /**
     * 92. Reverse Linked List II
     * For example:
     *  Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     *
     *  return 1->4->3->2->5->NULL.
     *
     *      1->2->3->4->5
     *      p  c  t
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;

        for (int i = 1; i < m; i++) {
            cur = cur.next;
            pre = pre.next;
        }

        for (int i = 0; i < n - m; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }
}