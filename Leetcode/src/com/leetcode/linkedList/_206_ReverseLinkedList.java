package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.List;

public class _206_ReverseLinkedList {

    /**
     * 206. Reverse Linked List
     * When: 2019/05/09
     * Review1: 2019/7/7
     * review2:10/30/2019
     * <p>
     * solution:
     * 依次遍历链表节点，每遍历一个节点即 逆置 一个节点
     * 具体步骤：
     * （1）备份head -> next;
     * （2）修改head -> next（指向到new_head)
     * （3）移动head与new_head;
     * <p>
     * Example:
     * <p>
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
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

    // 头插法。是用ListNode 节点而不是指针变量
    public ListNode reverseList2(ListNode head) {
        ListNode tempHead = new ListNode(0);
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = tempHead.next;
            tempHead.next = cur;
            cur = temp;
        }

        return tempHead.next;
    }

    //采取和92题中的一样策略！
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode first = pre.next;
        while (first.next != null) {
            ListNode temp = first.next;
            first.next = temp.next;
            temp.next = pre.next;
            pre.next = temp; // 这个位置不能变的原因，如果放到temp那一行的后面会导致pre.next已经赋值为temp，然后执行temp.next那个进入死循环
        }
        return dummy.next;
    }

    // recursion time:O(n) space:O(n)
    // 推荐视频演示 https://www.youtube.com/watch?v=MRe3UsRadKw
    public ListNode reverseList4(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList4(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
