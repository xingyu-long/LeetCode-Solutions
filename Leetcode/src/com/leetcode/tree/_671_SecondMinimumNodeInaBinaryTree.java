/*
 * @Date: 2020-03-27 22:41:50
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-03-27 22:43:34
 */
package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import com.leetcode.common.TreeNode;

public class _671_SecondMinimumNodeInaBinaryTree {

    // time:O(n) space:O(n)
    public int findSecondMinimumValue(TreeNode root) {
        int min = root.val;
        long res = Long.MAX_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.val > min && curr.val < res) {
                res = curr.val;
            }
            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
        }
        return res < Long.MAX_VALUE ? (int) res : -1; 
    }
}