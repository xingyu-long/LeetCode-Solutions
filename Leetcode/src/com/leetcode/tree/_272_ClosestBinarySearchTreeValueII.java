package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class _272_ClosestBinarySearchTreeValueII {

    /**
     *  272. Closest Binary Search Tree Value II
     *  When:2019/7/28
     *  Difficulty: Medium
     * @param root
     * @param target
     * @param k
     * @return
     */
    // 利用 PriorityQueue 来维护 K 个接近的节点 也可以用 LinkedList 来做
    // 利用中序遍历，从最小的节点开始计算
    public List<Integer> closestKValues(TreeNode root, double target, int k) {

        LinkedList<Integer> res = new LinkedList<>();
        helper(res, root, target, k);
        return res;
    }

    public void helper(LinkedList<Integer> res, TreeNode root, double target, int k) {
        if (root == null) return;
        helper(res, root.left, target, k);

        // inorder operation
        if (res.size() == k) {
            if (Math.abs(root.val - target) < Math.abs(res.peekFirst() - target)) {
                res.removeFirst();
            } else ;
        }
        res.add(root.val);
        helper(res, root.right, target, k);
    }
}
