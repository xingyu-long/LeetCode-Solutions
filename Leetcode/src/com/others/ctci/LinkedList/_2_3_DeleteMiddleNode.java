package com.ctci.LinkedList;

import com.leetcode.common.ListNode;

public class _2_3_DeleteMiddleNode {

    // 带有head的中间删除
    public static ListNode deleteMid(ListNode head) {
        // 至少有两个元素
        if (head == null || head.next == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = head, fast = head;
        // 利用一个prev来记录slow之前的地方
        ListNode prev = slow;
        while (slow.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return dummy.next;
    }

    //直接删除中间这个值
    public boolean delete(ListNode mid) {
        if (mid == null || mid.next == null) return false;
        ListNode next = mid.next;
        next.val = mid.val;
        // 跳过这个节点，相同于删除了mid
        mid.next = next.next;
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next =new ListNode(3);
        ListNode res = deleteMid(head);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
