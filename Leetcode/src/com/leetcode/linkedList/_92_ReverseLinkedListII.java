package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _92_ReverseLinkedListII {

    /**
     * 92. Reverse Linked List II
     * When: 2019/5/10
     * Review1: 2019/7/7
     * review2: 2019/8/31
     * <p>
     * For example:
     * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * <p>
     * return 1->4->3->2->5->NULL.
     * <p>
     * 1->2->3->4->5
     * p  c  t
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;

        //这里从1开始需要注意
        for (int i = 1; i < m; i++) {
            cur = cur.next;
            pre = pre.next;
        }
        // 这个太妙了，相当于每次移动cur（后面的那个数到前面去）
        for (int i = 0; i < n - m; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }

    //另外一种方法，其中内部交换的部分依然跟206题一致的用法
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        int numsOfChange = n - m + 1; //需要处理的元素
        ListNode preHead = null;
        ListNode res = head;

        // 移动preHead到目标移动的前一个位置
        while (head != null && --m > 0) {
            preHead = head;
            head = head.next;
        }
        //先移动第m到第n个元素
        ListNode afterTail = head;
        ListNode newHead = null; //这个是第m到第n个 这一段的head
        while (head != null && numsOfChange-- > 0) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        //翻转后的元素，连接后面剩下的部分
        afterTail.next = head;
        if (preHead != null) { //表示不是从第一个元素开始反转
            preHead.next = newHead;
        } else {
            res = newHead;
        }
        return res;
    }
}