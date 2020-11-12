package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 07/11/2020, 08/25/2020
 * @Description: 利用完全二叉树的性质，子树为2*i, 2*i + 1,然后再同时做offset操作
 **/
public class _662_MaximumWidthofBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        List<Integer> ids = new ArrayList<>(); // 每次记录当前层的第一个。
        return dfs(root, 0, 0, ids);
    }

    // 计算相对的距离，这样更加高效。
    public int dfs(TreeNode root, int level, int id, List<Integer> ids) {
        if (root == null) return 0;
        if (ids.size() == level) {
            ids.add(id);
        }
        int cur = id - ids.get(level) + 1;
        int left = dfs(root.left, level + 1, 2 * (id - ids.get(level)), ids);
        int right = dfs(root.right, level + 1, 2 * (id - ids.get(level)) + 1, ids);
        return Math.max(cur, Math.max(left, right));
    }
}
