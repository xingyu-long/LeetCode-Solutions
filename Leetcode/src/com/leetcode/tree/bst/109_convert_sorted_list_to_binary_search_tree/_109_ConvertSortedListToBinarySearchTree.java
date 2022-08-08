/*
 * @Date: 11/14/2019 21:02:42
 * @LastEditTime: 05/06/2021 08:45:58
 * @Description: Divide and conquer
 */

package com.leetcode.tree.bst;


import com.leetcode.common.ListNode;
import com.leetcode.common.TreeNode;

public class _109_ConvertSortedListToBinarySearchTree {
    // time:O(NlogN) space:O(logN)
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
