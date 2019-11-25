package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _25_ReverseNodesinKGroup {

    /**
     * 25. Reverse Nodes in k-Group
     * When:2019/9/2
     * Difficulty: Hard
     * 主要是利用pre, cur来进行
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, cur = head;
        dummy.next = head;
        int i = 1;

        while (cur != null) {
            if (i % k == 0) {
                pre = reverse(pre, cur.next);
                cur = pre.next;
            } else {
                cur = cur.next;
            }
            i++;
        }
        return dummy.next;
    }

    // 反转pre开始以及next之前的
    public ListNode reverse(ListNode pre, ListNode next) {
        ListNode first = pre.next;
        ListNode cur = first.next;
        while (cur != next) {
            first.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = first.next;
        }
        return first;
    }

    // 利用类似于swap pair的方式！
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int n = numOfNode(head);
        int times = n / k;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < times; i++) {
            ListNode first = prev.next;
            for (int j = 0; j < k - 1; j++) {
                ListNode temp = prev.next;
                prev.next = first.next;
                first.next = prev.next.next;
                prev.next.next = temp;
            }
            prev = first;
        }
        return dummy.next;
    }

    public int numOfNode(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
