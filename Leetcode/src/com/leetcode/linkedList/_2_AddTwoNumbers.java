/*
 * @Date: 11/02/2019 20:03:39
 * @LastEditTime: 01/12/2021 15:37:34
 * @Description: Linkedlist
 */
package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _2_AddTwoNumbers {

    // time: O(n) space: O(n)（因为新建节点）
    // 尽可能处理完全部节点，所以下面用的是 ||
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            curr.next = new ListNode(carry % 10);
            carry /= 10;
            curr = curr.next;
        }
        return dummy.next;
    }
}