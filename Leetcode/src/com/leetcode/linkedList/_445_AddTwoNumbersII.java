package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.Stack;

/**
 * @Date: 06/04/2020
 * @Description: LinkedList
 **/
public class _445_AddTwoNumbersII {

    // 不用reverse的话 利用stack来模拟，后面的head用的也是十分妙！
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

        ListNode head = null;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            if (!s1.isEmpty()) {
                carry += s1.pop();
            }
            if (!s2.isEmpty()) {
                carry += s2.pop();
            }
            ListNode node = new ListNode(carry % 10);
            node.next = head;
            carry /= 10;
            head = node;
        }
        return head;
    }
}
