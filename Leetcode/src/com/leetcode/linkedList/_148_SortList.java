package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _148_SortList {

    /**
     *  148. Sort List
     *  When: 2019/5/17
     *  Review1: 2019/7/8
     *  Difficulty: Medium

     *  Sort a linked list in O(n log n) time using constant space complexity.
     *
     *
     * @param head
     * @return
     */
    // time: O(nlog^n) space: O(n)
    public ListNode sortList(ListNode head) {
        // 使用归并排序
        if (head == null || head.next == null) return head;
        ListNode mid = findMiddle(head);
        ListNode next = mid.next;
        mid.next = null;
        return merge(sortList(head), sortList(next));
    }

    // 找中间的那个就应该这样写，并且下面一定是fast.next != null && fast.next.next != null
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = new ListNode(a.val);
                a = a.next;
            } else {
                cur.next = new ListNode(b.val);
                b = b.next;
            }
            cur = cur.next;
        }
        if (a == null) {
            cur.next = b;
        }
        if (b == null) {
            cur.next = a;
        }
        return dummy.next;
    }
}
