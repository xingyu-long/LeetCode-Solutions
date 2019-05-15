package com.leetcode.linkedList;

import com.leetcode.lib.ListNode;

public class _237_DeleteNodeInALinkedList {

    /**
     * 237. Delete Node in a Linked List
     * When: 2019/05/15
     *
     * solution:
     * 这里的node只是要被删除的node，所以就先通过获取next.val 然后使node.next = node.next.next 即可
     * @param node
     */
    public static void deleteNode(ListNode node) {
        if (node == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
