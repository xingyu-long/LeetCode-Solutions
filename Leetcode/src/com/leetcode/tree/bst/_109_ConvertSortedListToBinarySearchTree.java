package com.leetcode.tree.bst;


import com.leetcode.common.ListNode;
import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _109_ConvertSortedListToBinarySearchTree {

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * When: 2019/04/25
     *
     * solution:
     * 将其转化为array 然后使用108的方法
     *
     * Time: O(n)
     * space: O(n)
     *
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return helper(head, null);
    }
    
    public TreeNode helper(ListNode start, ListNode end) {
        if (start == end) return null;
        ListNode mid = findMid(start, end);
        TreeNode root = new TreeNode(mid.val);
        root.left = helper(start, mid);
        root.right = helper(mid.next, end);
        return root;
    }
    
    public ListNode findMid(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != tail && fast.next.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
