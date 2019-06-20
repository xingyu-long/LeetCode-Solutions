package com.leetcode.random;

import java.util.Random;

public class _382_LinkedListRandomNode {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    private ListNode head;
    private Random rmd;

    /**
     *  382. Linked List Random Node
        When: 2019/06/20

        solution：依然使用蓄水池抽样的方法
     * @param head
     */
    public _382_LinkedListRandomNode(ListNode head) {
        this.head = head;
        rmd = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode temp = head; // for moving forward
        int res = temp.val;
        int i = 1;
        while (temp.next != null) {
            temp = temp.next;
            if (rmd.nextInt(++i) == 0) {
                res = temp.val;
            }
        }
        return res;
    }
}
