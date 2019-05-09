package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _206_ReverseLinkedList {

    /**
     * 206. Reverse Linked List
     * When: 2019/05/09
     *
     * Reverse a singly linked list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     * @param head
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }
}
