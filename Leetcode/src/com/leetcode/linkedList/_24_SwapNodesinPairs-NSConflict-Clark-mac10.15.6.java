package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

/**
 * @Date: 2019/05/10, 2019/7/8, 2019/7/23, 07/13/2020
 * @Description: Sort, List
 **/
public class _24_SwapNodesinPairs {

    // time: O(n) space: O(1)
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy, curr = head, next = null;
        while (curr != null && curr.next != null) {
            next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;

            prev = curr;
            curr = prev.next;
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