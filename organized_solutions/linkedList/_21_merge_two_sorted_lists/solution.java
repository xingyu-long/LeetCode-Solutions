package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _21_MergeTwoSortedLists {

    /**
     *  21. Merge Two Sorted Lists
     *  when: 2019/05/16
     *  Review1: 2019/7/7
     *  Review2: 2019/7/24
     *  Difficulty: Easy
     *
     * <p>
     * <p>
     * Merge two sorted linked lists and return it as a new list. The new list should be made
     * by splicing together the nodes of the first two lists.
     * <p>
     * solution:
     * 1. 类似于归并排序
     * 2. 递归的方式 （没写）
     *
     * @param l1
     * @param l2
     * @return
     */
    // time: O(l1 + l2) space: O(l1 + l2)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        //这个条件需要确认。
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 再接上剩下的
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }


    // recursion
    // time:O(n) space:O(n);
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
