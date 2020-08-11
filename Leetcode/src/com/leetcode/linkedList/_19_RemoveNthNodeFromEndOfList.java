package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

/**
 * @Date: 07/11/2020
 * @Solution: 利用fast先走，然后再同步slow一起走，这样保持着n的距离。
 **/
public class _19_RemoveNthNodeFromEndOfList {

    // 需要考虑n等于节点个数的case
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