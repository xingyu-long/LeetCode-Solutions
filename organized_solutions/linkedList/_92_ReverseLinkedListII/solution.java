package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _92_ReverseLinkedListII {

    /**
     * 92. Reverse Linked List II
     * When: 2019/5/10
     * Review1: 2019/7/7
     * review2: 2019/8/31
     * <p>
     * For example:
     * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * <p>
     * return 1->4->3->2->5->NULL.
     * <p>
     * 1->2->3->4->5
     * p  c  t
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    // time: O(n) space: O(1)
    //另外一种方法，其中内部交换的部分依然跟206题一致的用法
    // 更加的易懂。
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }
        int count = n - m;
        ListNode cur = prev.next;
        while (count-- > 0) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }
}