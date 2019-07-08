package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

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

    // 先反转，将其低位放到前面
    public ListNode plusOne2(ListNode head) {
        head = reverse(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        int carry = 1;
        // 对于 9->9->9 这种情况carry一直在 最后会生成新节点
        // 对于 1->2->3这种情况 则carry 会为0 直到cur走完，结束
        while (carry > 0 || cur.next != null) {
            if (cur.next != null) {
                cur = cur.next;
                carry += cur.val;
                cur.val = carry % 10;
                carry /= 10;
            } else {
                cur.next = new ListNode(carry);
                cur = cur.next;
                carry = 0;
            }
        }
        return reverse(dummy.next);
    }

    public ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
