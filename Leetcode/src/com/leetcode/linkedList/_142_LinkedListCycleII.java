package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _142_LinkedListCycleII {

    /**
     *  142. Linked List Cycle II
     *  When: 2019/05/17
        Review1: 2019/7/7
        Difficulty: Medium

     * solution:
     * 首先通过two pointer的方法得到slow和fast相等点，这时候已经有闭环。然后head从头开始出来，当head和slow相遇则就是起始点
     * https://www.cnblogs.com/hiddenfox/p/3408931.html
     * @param head
     * @return
     */
    // time: O(n) space: O(1)
    public ListNode detectCycle(ListNode head) {
        // 还是利用fast和slow 来找到那个点，然后使用head与slow
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode temp = head;
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
