package com.leetcode.tree;

import com.leetcode.common.Nnode;

public class _559_MaximumDepthofNaryTree {
    public int maxDepth(Nnode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    public int dfs(Nnode root) {
        if (root == null) return 0;
        int max = 0;
        for (Nnode node : root.children) {
            max = Math.max(max, dfs(node));
        }
        return max + 1;
    }
}
