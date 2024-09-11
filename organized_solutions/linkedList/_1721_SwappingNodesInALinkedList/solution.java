/*
 * @Date: 03/14/2021 09:34:21
 * @LastEditTime: 03/14/2021 09:35:08
 * @Description: Swap, LinkedList
 */
package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _1721_SwappingNodesInALinkedList {
    // 比较巧妙的保持相对距离的方法。
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode first = null, second = null;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            second = (second == null ? null : second.next);
            if (--k == 0) {
                first = curr;
                second = head;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        return head;
    }
}
