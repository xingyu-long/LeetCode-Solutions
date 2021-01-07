/*
 * @Date: 01/02/2021 10:04:32
 * @LastEditTime: 01/02/2021 10:04:53
 * @Description: BFS/DFS
 */
package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.leetcode.common.TreeNode;

public class _1379_FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // BFS
        if (original == null || cloned == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(original);
        queue.offer(cloned);
        while (!queue.isEmpty()) {
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();
            if (first.equals(target)) {
                return second;
            }
            if (first.left != null) {
                queue.offer(first.left);
                queue.offer(second.left);
            }
            if (first.right != null) {
                queue.offer(first.right);
                queue.offer(second.right);
            }
        }
        return null;
    }
}

