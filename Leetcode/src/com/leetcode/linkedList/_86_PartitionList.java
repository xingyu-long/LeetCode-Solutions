package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _86_PartitionList {
    /**
     * 86. Partition List
     * When: 2019/05/20
     * Review1: 2019/7/7

        Difficulty: Medium

        Given a linked list and a value x, partition it such that all nodes less than x come before nodes
        greater than or equal to x.

        You should preserve the original relative order of the nodes in each of the two partitions.

        For example,
        Given 1->4->3->2->5->2 and x = 3,
        return 1->2->2->4->3->5.
     *
     *  solution:
     *  新建两个左右的点，然后记录其比它小和比它大的情况
     *  然后两个表连起来（这里要注意需要用curRight/left来移动
     *  因为本事不是有序的，最后出来左边 小于x 右边大于等于x即可
     * @param head
     * @param x
     * @return
     */
    //time:O(n) space:O(n)
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        // 分成左右
        ListNode dummyLeft = new ListNode(0);
        ListNode dummyRight = new ListNode(0);
        // curXXX用来移动
        ListNode curLeft = dummyLeft;
        ListNode curRight = dummyRight;
        while (head != null) {
            if (head.val < x) {
                curLeft.next = head;
                curLeft = curLeft.next;
            } else {
                curRight.next = head;
                curRight = curRight.next;
            }
            head = head.next;
        }
        curRight.next = null;
        curLeft.next = dummyRight.next;
        return dummyLeft.next;
    }

    // 通过prev跳跃。
    public ListNode partition2(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while (prev != null && prev.next != null) {
            if (prev.next.val < x) {
                cur.next = new ListNode(prev.next.val);
                cur = cur.next;
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        cur.next = dummy.next;
        return newHead.next;
    }
}