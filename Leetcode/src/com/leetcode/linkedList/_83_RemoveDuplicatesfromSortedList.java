package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _83_RemoveDuplicatesfromSortedList {

    /**
     * 83. Remove Duplicates from Sorted List
     * When: 2019/05/15
     * <p>
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     * <p>
     * For example,
     * Given 1->1->2, return 1->2.
     * Given 1->1->2->3->3, return 1->2->3.
     * <p>
     * solution:
     * 1. 利用hashset
     * 2. 因为有序，所以前后比较
     *
     * @param head
     * @return
     */
    // time: O(n) space: O(1)
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    // time: O(n) space: (n)
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        Set<Integer> set = new HashSet<>();
        while (pre.next != null) {
            if (set.contains(pre.next.val)) {
                pre.next = pre.next.next;
            } else {
                set.add(pre.next.val);
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}