package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _21_MergeTwoSortedLists {

    /**
     * 21. Merge Two Sorted Lists
     * when: 2019/05/16
     *
     * Merge two sorted linked lists and return it as a new list. The new list should be made
     * by splicing together the nodes of the first two lists.
     *
     * solution:
     * 1. 类似于归并排序
     * 2. 递归的方式 （没写）
     * @param l1
     * @param l2
     * @return
     */
    // time: O(l1 + l2) space: O(l1 + l2)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy =  new ListNode(0);
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
        } else {
            cur.next = l2;
        }
        return dummy.next;
    }


}
