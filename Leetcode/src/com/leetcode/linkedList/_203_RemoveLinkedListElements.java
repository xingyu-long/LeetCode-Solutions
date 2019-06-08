package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _203_RemoveLinkedListElements {

    /**
     * 203. Remove Linked List Elements
     * When: 2019/05/16
     *
     * Remove all elements from a linked list of integers that have value val.
     *
     *      Example
     *      Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
     *      Return: 1 --> 2 --> 3 --> 4 --> 5
     *
     * solution:
     * 利用一个前置节点pre以及一开始的dummy（用来返回使用）然后通过pre.next.val 来进行比较
     * @param head
     * @param val
     * @return
     */
    // time: O(n) space:O(1)
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}
