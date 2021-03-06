package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _328_OddEvenLinkedList {

    /**
     *  328. Odd Even Linked List
     *  When: 2019/7/8
        Difficulty: Medium

     Example:

     Given 1->2->3->4->5->NULL,
     return 1->3->5->2->4->NULL.

     1 -> 3    2 -> 4

     3 -> 4 -> 5 -> 6


     * @param head
     * @return
     */
    //time: O(n) space: O(1)
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        // 这里面的even最后就直接连接了null。
        while (even != null && even.next != null) {
            // 这里需要先计算odd的情况，因为不然even会把odd的next打乱
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
