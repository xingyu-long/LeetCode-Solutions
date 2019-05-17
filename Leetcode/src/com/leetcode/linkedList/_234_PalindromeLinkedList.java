package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _234_PalindromeLinkedList {

    /**
     * 234. Palindrome Linked List
     * When: 2019/05/16
     * Input: 1->2
     * Output: false
     * Input: 1->2->2->1
     * Output: true
     *
     * solution:
     * 首先找到中间的部分，将其后面的反转，然后一一比较，如果有不同的，那就不是回文数。
     * @param head
     * @return
     */
    // time: O(n) space: O(1)
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next);

        ListNode p = head;
        ListNode q = middle.next;
        while (p != null && q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }

    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}