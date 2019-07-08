package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

public class _237_DeleteNodeInALinkedList {

    /**
     *  237. Delete Node in a Linked List
     *  When: 2019/05/15
        Review:2019/7/8
        Difficulty: Easy

        solution:
        (1) 这里的node只是要被删除的node，所以就先通过获取next.val 然后使node.next = node.next.next 即可
        不能跳跃着做，因为这里是给的当前节点并非head，所以没有办法循环
     * @param node
     */
    // time: O(1) space: O(1)
    public static void deleteNode(ListNode node) {
        if (node == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
