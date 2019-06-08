package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _160_IntersectionofTwoLinkedLists {

    /**
     * 160. Intersection of Two Linked Lists
     * When: 2019/05/16
     *
     *
     * For example, the following two linked lists:
     *
     *      A:          a1 → a2
     *                          ↘
     *                          c1 → c2 → c3
     *                          ↗
     *      B:     b1 → b2 → b3
     *      begin to intersect at node c1.
     *
     * solution: 先统一长度，然后再逐步对比。
     * @param headA
     * @param headB
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p1 = headA, p2 = headB;
        int numP1 = numberOfNode(p1);
        int numP2 = numberOfNode(p2);
        if (numP1 > numP2) {
            while (numP1 != numP2) {
                p1 = p1.next;
                numP1--;
            }
        } else {
            while (numP2 != numP1) {
                p2 = p2.next;
                numP2--;
            }
        }
        //这时候已经相同的长度了
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public int numberOfNode(ListNode head) {
        int n = 0;
        while (head != null) {
            n += 1;
            head = head.next;
        }
        return n;
    }
}