package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _24_SwapNodesinPairs {

    /**
     * 24.Swap Nodes in Pairs
     * When: 2019/05/10
     * Review1: 2019/7/8
     * Review2: 2019/7/23
     * review3: 2019/8/31
     * <p>
     * Given a linked list, swap every two adjacent nodes and return its head.
     * <p>
     * For example,
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
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
            // 每一次需要保持其 first 更新
            first = pre.next;
            pre.next = first.next;
            first.next = pre.next.next;
            pre.next.next = first;
            pre = first;
        }
        return dummy.next;
    }

    // recursion time:O(n) space:O(n)
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode nextTwo = head.next.next;

        newHead.next = head;
        head.next = swapPairs2(nextTwo);
        return newHead;
    }
}