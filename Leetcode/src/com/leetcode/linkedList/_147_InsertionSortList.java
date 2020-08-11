package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _147_InsertionSortList {

    /**
     * 147. Insertion Sort List
     * When: 2019/05/22
     * Difficulty: Medium
     * Review1: 2019/7/11
     * review2:2019/9/2
     *
     * <p>
     * solution: 主要利用三个指针，实现插入排序
     * 注意使用pre来从前循环
     * @param head
     * @return
     */
    //time: O(n^2) space: O(1)
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre; // 用来找比当前小的最大的位置（从这里连接目标元素）
        ListNode temp; // 用来存储当前的下一个
        while (cur != null && cur.next != null) {
            if (cur.val <= cur.next.val) {
                cur = cur.next;
            } else {
                temp = cur.next;
                cur.next = temp.next; // 把temp元素孤立出来，看插入的位置。
                pre = dummy;
                // 直到找到比temp大的位置
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
