package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _82_RemoveDuplicatesfromSortedListII {

    /**
     * 82. Remove Duplicates from Sorted List II (83. Remove Duplicates from Sorted List: follow up)
     * When: 2019/05/16
     *
     *  For example,
     *      Given 0->1->2->3->3->4->4->5, return 1->2->5.
     *      Given 0->1->1->1->2->3, return 2->3.
     *
     * solution:
     * 通过pre指针开始，并且扫描后面的pre.next以及pre.next.next 中间一个while 这样跳过所有相同的值
     *
     * @param head
     * @return
     */

    // time: O(n) space: O(1)
    public ListNode deleteDuplicates(ListNode head) {
        // 这里相当于通过while循环，把那些相同的数排除
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            if (pre.next.val == pre.next.next.val) {
                int sameNum = pre.next.val;
                // 越过那些相同的数字
                while(pre.next != null && pre.next.val == sameNum) {
                    pre.next = pre.next.next;
                }
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}