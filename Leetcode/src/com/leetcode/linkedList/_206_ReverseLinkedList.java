package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _206_ReverseLinkedList {

    /**
     *  206. Reverse Linked List
     *  When: 2019/05/09
        Review1: 2019/7/7

        solution:
            依次遍历链表节点，每遍历一个节点即 逆置 一个节点
            具体步骤：
            （1）备份head -> next;
            （2）修改head -> next（指向到new_head)
            （3）移动head与new_head;

        Example:

        Input: 1->2->3->4->5->NULL
        Output: 5->4->3->2->1->NULL
     *
     *
     * @param head
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = nextTemp;
        }

        return newHead;
    }
}
