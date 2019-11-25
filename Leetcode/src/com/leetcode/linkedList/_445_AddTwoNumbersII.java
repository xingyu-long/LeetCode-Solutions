package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.Stack;

public class _445_AddTwoNumbersII {
    // 不用reverse的话 利用stack来模拟
    // time: O(n) space: O(n)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode cur = new ListNode(0);
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                carry += s1.pop();
            }
            if (!s2.isEmpty()) {
                carry += s2.pop();
            }
            cur.val = carry % 10;
            ListNode head = new ListNode(carry / 10);
            head.next = cur;
            cur = head;
            carry /= 10;
        }
        return cur.val == 0 ? cur.next : cur;
    }
}
