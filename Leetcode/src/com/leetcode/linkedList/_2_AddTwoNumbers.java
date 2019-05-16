package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _2_AddTwoNumbers {

    /**
     * 2. Add Two Numbers
     * When: 2019/05/16
     *
     *
     *  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     *      Output: 7 -> 0 -> 8
     *
     * @param l1
     * @param l2
     * @return
     */
    // time: O(n) space: O(n)（因为新建节点）
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int sum = 0; // 计算其值，求余得到进位，求模得到剩下的数字
        ListNode cur = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }

            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }

            cur.next = new ListNode(sum % 10);
            sum /= 10;
            cur = cur.next;
        }
        // 最后如果出现两位相加 进位的情况
        if (sum == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }
}