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

    // time:O(n) space:O(n)
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        // hashset
        HashSet<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while (cur != null) { //这种方法只能用在set里面
            if (!set.add(cur.val)) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }


    public static ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = head, cur = head.next;
        while (cur != null) { // 和hashset思路一样，主要看pre是否更新
            if (pre.val == cur.val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
    public static void main(String[] args) {
        ListNode root = new ListNode(0);
        root.next = new ListNode(0);
        root.next.next = new ListNode(0);
        deleteDuplicates3(root);
    }
}