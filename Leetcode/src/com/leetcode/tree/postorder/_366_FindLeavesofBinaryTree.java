package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _366_FindLeavesofBinaryTree {
    /**
     *  366. Find Leaves of Binary Tree
     *  When:2019/7/27
     *  Difficulty: Medium
        Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves,
        repeat until the tree is empty.

     Example:
     Given binary tree
          1
         / \
        2   3
       / \
      4   5

     null   -1

     Returns [4, 5, 3], [2], [1].
     * @param root
     * @return
     */
    // 如何用postOrder记录其中间非叶子节点
    // 计算每个节点的depth 当 depth == res.size() 则表示是下一层

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root);
        return res;
    }

    public int helper(List<List<Integer>> res, TreeNode root) {
        if (root == null) return -1; // 加1之后为0 然后与res.size()刚好对应
        int left = helper(res, root.left);
        int right = helper(res, root.right);
        int level = Math.max(left, right) + 1;
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        // 清空左右节点
        root.left = null;
        root.right = null;
        return level;
    }
}
