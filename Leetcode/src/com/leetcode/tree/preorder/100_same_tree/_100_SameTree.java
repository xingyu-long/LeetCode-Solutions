package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 07/13/2020
 * @Description: preorder
 **/
public class _100_SameTree {

    // time:O(n) space: best O(lgN) worse O(n)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true; // 主要依靠这个返回
        }
        // 需要注意这个
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false; // 之前写错成了 p == q return true
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // BFS
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) {
                continue; // 这里很重要，因为后面可能有违反啊
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }
            queue.offer(t1.left);
            queue.offer(t2.left);
            queue.offer(t1.right);
            queue.offer(t2.right);
        }
        return true;
    }
}