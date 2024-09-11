package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.HashMap;

public class _1171_RemoveZeroSumConsecutiveNodesfromLinkedList {
    public ListNode removeZeroSumSublists(ListNode head) {
        // time:O(n) space:O(n)
        // use prefix sum.
        // if we meet the same prefix, it means 0 sums between it.
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        int prefix = 0;
        HashMap<Integer, ListNode> map = new HashMap<>();
        while (curr != null) {
            prefix += curr.val;
            if (map.containsKey(prefix)) {
                curr = map.get(prefix).next;
                int p = prefix + curr.val;
                while (p != prefix) {
                    map.remove(p);
                    curr = curr.next;
                    p += curr.val;
                }
                // skip
                map.get(prefix).next = curr.next;
            } else {
                map.put(prefix, curr);
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    // 这里有一种two pass也很酷了
    public ListNode removeZeroSumSublists2(ListNode head) {
        int prefix = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        HashMap<Integer, ListNode> map = new HashMap<>();
        map.put(0, dummy);
        for (ListNode i = dummy; i != null; i = i.next) {
            prefix += i.val;
            map.put(prefix, i);
        }
        prefix = 0;
        for (ListNode i = dummy; i != null; i = i.next) {
            prefix += i.val;
            i.next = map.get(prefix).next;
        }
        return dummy.next;
    }
}
