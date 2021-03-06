package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 05/29/2020
 * @Description: Complete tree
 **/
public class _958_CheckCompletenessofaBinaryTree {
    // there should not be any node after we met an empty one.
    // time:O(N) space:O(N)
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                end = true;
            } else {
                if (end) {
                    return false; // 表示在null之后还有点，则错误！
                }
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        return true;
    }
}
