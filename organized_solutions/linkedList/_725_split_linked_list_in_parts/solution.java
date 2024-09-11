package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _725_SplitLinkedListinParts {
    // 先看整除情况下每个能有几个，然后再diff每次添加一个给它。
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int total = numOfNodes(root);
        int num = total / k ;
        int diff = total % k;
        ListNode cur = root;
        ListNode prev = null;
        for (int i = 0; i < k && cur != null; i++, diff--) {
            res[i] = cur;
            for (int j = 0; j < num + (diff > 0 ? 1: 0); j++) {
                prev = cur;
                cur = cur.next;
            }
            prev.next = null;
        }
        return res;
    }

    public int numOfNodes(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
