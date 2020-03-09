package com.leetcode.tree;

import com.leetcode.common.ListNode;
import com.leetcode.common.TreeNode;

public class _1367_LinkedListinBinaryTree {

    /**
     * When: 03/01/2020, 03/09/2020
     * @param head
     * @param root
     * @return
     */
    // time: O(n * min(L, H)) L: length of list, H = tree Height
    // space:O(H)
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null && root == null) return true;
        if (head == null || root == null) return false;
        if (head.val == root.val) {
            if (dfs(head, root))
                return true; // 走每一个可能List开始的地方
            else
                return isSubPath(head, root.left) || isSubPath(head, root.right);
        } else {
            return isSubPath(head, root.left) || isSubPath(head, root.right);
        }
    }

    public boolean dfs(ListNode head, TreeNode root) {
        if (head == null && root == null) return true;
        if (head == null && root != null) return true;
        if (head != null && root == null) return false;
        if (head.val != root.val) return false;
        boolean left = dfs(head.next, root.left);
        boolean right = dfs(head.next, root.right);
        return left || right;
    }
}
