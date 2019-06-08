package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _141_LinkedListCycle {

    /**
     * 141. Linked List Cycle
     * when: 2019/05/09
     *
     * Given a linked list, determine if it has a cycle in it.
     *
     *
     * @param head
     * @return
     */
    // time: O(n) space: O(n)
    public boolean hasCycle2(ListNode head) {
        // 利用hashset
        Set<ListNode> nodeSeen = new HashSet<>();
        while (head != null) {
            if (nodeSeen.contains(head)) {
                return true;
            } else {
                nodeSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    // time: O(n) space: O(1)
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
