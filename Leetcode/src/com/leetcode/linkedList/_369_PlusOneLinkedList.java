package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.Stack;

public class _369_PlusOneLinkedList {
    /**
     *  369. Plus One Linked List
     *  When:2019/7/7
     *  Difficulty: Medium

     Input:
     1->2->3

     Output:
     1->2->4

     * @param head
     * @return
     */
    // time: O(n) space:O(1)
    // 这个解法比较🐂
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            j = j.next;
            if (j.val != 0) i = j;
        }
        i.val++;
        i = i.next;
        while (i != null) {
            i.val = 0;
            i = i.next;
        }
        if (dummy.val == 0) return dummy.next;
        return dummy;
    }

    public static ListNode plusOne2(ListNode head) {
        if (head == null) return null;
        head = reverse2(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int carry = 1;
        ListNode prev = dummy;
        // 可能存在加位的操作，所以用prev.
        while (prev.next != null) {
            carry += prev.next.val;
            prev.next.val = carry % 10;
            carry /= 10;
            prev = prev.next;
        }
        if (carry != 0) {
            prev.next = new ListNode(carry);
        }

        return reverse2(dummy.next);
    }

    public static ListNode reverse2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    // 利用stack
    public ListNode plusOne3(ListNode head) {
        if (head == null) return head;
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head;
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }

        int carry = 1;
        ListNode newHead = null;
        while (!stack.isEmpty() || carry != 0) {
            if (!stack.isEmpty()) {
                carry += stack.pop();
            }
            ListNode node = new ListNode(carry % 10);
            node.next = newHead;
            newHead = node;
            carry /= 10;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(9);
        root.next = new ListNode(9);
        root.next.next = new ListNode(9);
        ListNode cur = root;
        cur = plusOne2(root);
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
