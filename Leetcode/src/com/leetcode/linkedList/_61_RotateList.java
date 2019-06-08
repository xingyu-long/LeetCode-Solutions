package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _61_RotateList {

    /**
     * 61. Rotate List
     * When: 2019/05/20
     *
     * For example:
     *   Given 1->2->3->4->5->NULL and k = 2,
     *   return 4->5->1->2->3->NULL.
     *
     * solution:
     * 首先计数，并且将链表连起来，然后走到合适位置 断开
     *  @param head
     * @param k
     * @return
     */
    //time: O(n) space: O(1)
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode index = head; // 用来计数时使用
        int len = 1;
        while (index.next != null) { //这样的话 最后index在最后一个元素
            index = index.next;
            len++;
        }
        index.next = head;
        // 尽管是要走到len - k % len 这个位置 但只需要 前面-1 这么多步而已
        for (int i = 1; i < len - k % len; i++) {
            head = head.next;
        }
        ListNode res = head.next;
        head.next = null;
        return res;
    }
}
