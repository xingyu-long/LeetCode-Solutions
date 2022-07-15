/*
 * @Date: 06/20/2019 07:25:50
 * @LastEditTime: 12/02/2020 14:52:59
 * @Description: 蓄水池抽样
 */
package com.leetcode.random;

import java.util.Random;
import com.leetcode.common.ListNode;

public class _382_LinkedListRandomNode {

    private ListNode head;
    private Random rmd;

    public _382_LinkedListRandomNode(ListNode head) {
        this.head = head;
        rmd = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int count = 0;
        int res = -1;
        ListNode curr = head;
        while (curr != null) {
            if (rmd.nextInt(++count) == 0) {
                res = curr.val;
            }
            curr = curr.next;
        }
        return res;
    }
}
