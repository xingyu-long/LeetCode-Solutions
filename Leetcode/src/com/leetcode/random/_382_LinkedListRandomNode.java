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
        ListNode curr = head; // for moving forward
        int res = curr.val;
        int i = 1;
        while (curr.next != null) {
            curr = curr.next;
            if (rmd.nextInt(++i) == 0) {
                res = curr.val;
            }
        }
        return res;
    }
}
