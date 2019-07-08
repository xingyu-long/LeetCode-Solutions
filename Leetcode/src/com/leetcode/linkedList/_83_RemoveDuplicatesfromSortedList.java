package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _83_RemoveDuplicatesfromSortedList {

    /**
     *  83. Remove Duplicates from Sorted List
     *  When: 2019/05/15
        Review1:2019/7/8
        Difficulty: Easy

     Given a sorted linked list, delete all duplicates such that each element appear only once.

     For example,
     Given 1->1->2, return 1->2.
     Given 1->1->2->3->3, return 1->2->3.

     solution:
     因为有序，所以前后比较
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
}