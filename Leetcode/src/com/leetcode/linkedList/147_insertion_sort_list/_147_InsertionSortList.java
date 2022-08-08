package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

/**
 * @Date: 07/13/2020
 * @Description: Sort, List
 **/
public class _147_InsertionSortList {

    //time: O(n^2) space: O(1)
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = head, next = null;
        while (curr != null && curr.next != null) {
            if (curr.val <= curr.next.val) {
                curr = curr.next;
            } else {
                int val = curr.next.val;
                prev = dummy; // 利用prev找到插入点。
                while (prev.next != null && prev.next.val < val) {
                    prev = prev.next;
                }
                // 插入curr.next这个节点
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
        }
        return dummy.next;
    }
}
