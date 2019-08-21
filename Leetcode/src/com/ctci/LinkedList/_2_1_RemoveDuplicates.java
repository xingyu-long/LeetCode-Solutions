package com.ctci.LinkedList;

import com.leetcode.common.ListNode;

import java.util.HashSet;

public class _2_1_RemoveDuplicates {
    /**
     * 2019/8/20
     *
     * Remove Duplicates: Write code to remove duplicates from an unsorted linked list.
     *
     * solution:
     * 1. 利用HashSet做标记然后用 previous 做标记
     * 2.
     * @param head
     * @return
     */
    // time:O(n) space:O(n)
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // use hashSet 这里不要存储ListNode，因为val一样，地址也会不同所以可以加入set
        HashSet<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            if (set.contains(cur.val)) {
                prev.next = cur.next;
            } else {
                set.add(cur.val);
                prev = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // time:O(n ^ 2) space:O(1) 相当于每次就是检查 cur 后面的
    public static ListNode deleteDuplicates2(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            ListNode fast = cur;
            while (fast.next != null) {
                if (fast.next.val == cur.val) {
                    fast.next = fast.next.next;
                } else {
                    fast = fast.next;
                }
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        deleteDuplicates(head);
    }
}
