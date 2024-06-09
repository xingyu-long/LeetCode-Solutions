package com.ctci.LinkedList;

import com.leetcode.common.ListNode;

public class _2_2_ReturnKthtoLast {

    /**
     * 2019/8/20
     *
     * Return Kth to Last: Implement an algorithm to find the kth to
     * last element of a singly linked list.
     *
     * solution:
     * recursion
     * @param head
     * @param k
     * @return
     */
    // time:O(k) space:O(k)
    public static ListNode KthToLast(ListNode head, int k) {
        if (k == 0) {
            return head;
        }
        if (head == null) {
            return null;
        }
        ListNode node = KthToLast(head.next, --k);
        return node;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(KthToLast(head, 1).val);
    }
}
