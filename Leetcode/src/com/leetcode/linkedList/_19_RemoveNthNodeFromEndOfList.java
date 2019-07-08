package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _19_RemoveNthNodeFromEndOfList {

    /**
     *  19. Remove Nth Node From End of List
     *  When: 2019/05/15
        Review1: 2019/7/8
        Difficulty: Medium

        Given linked list: 1->2->3->4->5, and n = 2.
        After removing the second node from the end, the linked list becomes 1->2->3->5.

     * solution:
     * 首先计算出有多少个元素，然后相减，遍历删除（要考虑一种情况，则就是删除第一个元素即n == num）
     *
     * @param head
     * @param n
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int num = numberOfNode(node);
        if (num == n) {
            head = head.next;
        } else {
            int diff = num - n;
            for (int i = 1; i < diff; i++) {
                node = node.next;
            }
            node.next = node.next.next;
        }

        return head;
    }

    public static int numberOfNode(ListNode node) {
        int n = 0;
        while (node != null) {
            n += 1;
            node = node.next;
        }
        return n;
    }

    // 相当于是 fast后面走的长度，其实就是总长度减去n的距离。
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy;
        ListNode fast = dummy;
        dummy.next = head;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}