package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.PriorityQueue;

public class _23_MergekSortedLists {
    /**
     *  23. Merge k Sorted Lists
     *  When:2019/7/7
        Difficulty: Hard

        solution:
        (1) 使用合并排序
        (2) 使用PriorityQueue （从小到大排序）

     time : O(nlogk) where k is the number of linked lists
     space : O(n)


     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 使用PriorityQueue实现
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }
}
