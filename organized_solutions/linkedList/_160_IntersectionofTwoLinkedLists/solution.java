package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _160_IntersectionofTwoLinkedLists {

    /**
     * 160. Intersection of Two Linked Lists
     * When: 2019/05/16
     * Review1: 2019/7/7
     * review2: 2019/8/30
        Difficulty: Easy
        solution: 先统一长度，然后再逐步对比。

      For example, the following two linked lists:

           A:          a1 → a2
                               ↘
                               c1 → c2 → c3
                               ↗
           B:     b1 → b2 → b3
           begin to intersect at node c1.

     * @param headA
     * @param headB
     * @return
     */
    // time: O(m + n) space: O(1)
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

    // 写法和上面差不多
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = numberOfNode(headA);
        int lenB = numberOfNode(headB);
        int diff = Math.abs(lenA - lenB);
        if (lenA < lenB) {
            for (int i = 0; i < diff; i++) {
                headB = headB.next;
            }
        } else if (lenA > lenB) {
            for (int i = 0; i < diff; i++) {
                headA = headA.next;
            }
        }
        // 比较的是地址 非val！！！记住
        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    // time:O(m + n) space:O(1)
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //相当于a和b相加的情况下，肯定会有相遇情况出现
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}