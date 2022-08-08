/*
 * @Date: 11/13/2019 19:46:00
 * @LastEditTime: 07/17/2022 17:21:00
 * @Description: Postorder, path
 */
package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _366_FindLeavesofBinaryTree {
    // 如何用postOrder记录其中间非叶子节点
    // 计算每个节点的depth 当 depth == res.size() 则表示是下一层
    // time:O(n) space:O(n)
    public List<List<Integer>> findLeaves(TreeNode root) {
        // 利用maxDepth来做
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        dfs(root, res);
        return res;
    }

    public int dfs(TreeNode root, List<List<Integer>> res) {
        if (root == null)
            return 0;
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        int max = Math.max(left, right);
        if (max == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(max).add(root.val);
        return max + 1;
    }
}
