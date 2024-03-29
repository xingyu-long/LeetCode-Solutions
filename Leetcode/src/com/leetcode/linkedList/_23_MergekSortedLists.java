package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.PriorityQueue;

public class _23_MergekSortedLists {
    /**
     * 23. Merge k Sorted Lists
     * When:2019/7/7
     * review1:2019/9/2
     * Difficulty: Hard
     *
     * solution:
     * (1) 使用合并排序
     * (2) 使用PriorityQueue （从小到大排序）
     * <p>
     * time : O(nlogk) where k is the number of linked lists
     * space : O(n)
     *
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
            if (list != null) { // 这里需要判断是否为空，防止[[]]这样的情况
                pq.add(list);
            }
        }
        while (!pq.isEmpty()) {
            //放入到返回结果中
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }

    /**
     * 合并排序
     */
    // 另外有一个sort list 需要用ListNode然后找中点
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    public ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo > hi) return null; // 这里需要注意。
        if (lo == hi) return lists[lo];
        int mid = (hi - lo) / 2 + lo;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
