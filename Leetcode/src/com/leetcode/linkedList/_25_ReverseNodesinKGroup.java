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
        int i = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (i % k == 0) {
                prev = reverse(prev, cur);
                cur = prev.next; // cur肯定要在prev后面开始，因为前面改变过了
            } else {
                cur = cur.next;
            }
            i++;
        }
        return dummy.next;
    }

    // reverse nodes from PREV to end
    public ListNode reverse(ListNode prev, ListNode end) {
        ListNode cur = prev.next;
        while (prev.next != end) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return cur;
    }

    // 利用类似于swap pair的方式！
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        // 计算其个数然后再看是否需要反转
        int total = getNum(head);
        int times = total / k;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < times; i++) {
            ListNode cur = prev.next;
            for (int j = 0; j < k - 1; j++) {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = cur;
        }
        return dummy.next;
    }

    public int getNum(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }
}
