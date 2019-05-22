package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _147_InsertionSortList {

    /**
     * 147. Insertion Sort List
     * When: 2019/05/22
     *
     * solution: 主要利用三个指针，实现插入排序
     * @param head
     * @return
     */
    //time: O(n) space: O(1)
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = null; // 用来找比当前小的最大的位置（从这里连接目标元素）
        ListNode temp = null; // 用来存储当前的下一个
        while (cur != null && cur.next != null) {
            if (cur.val <= cur.next.val) {
                cur = cur.next;
            } else {
                temp = cur.next;
                cur.next = temp.next;
                pre = dummy;
                while (pre.next.val <= temp.val) {
                    pre = pre.next; // 一直查找小于temp里面最大的数
                }
                temp.next = pre.next;
                pre.next = temp;
            }
        }
        return dummy.next;
    }
}
