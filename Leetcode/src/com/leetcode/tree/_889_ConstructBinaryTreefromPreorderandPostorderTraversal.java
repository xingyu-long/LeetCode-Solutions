package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _889_ConstructBinaryTreefromPreorderandPostorderTraversal {
    // 最差的时候 O(n^2) avg: nlogn
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || pre.length == 0 ||
                post == null || post.length == 0) return null;
        int pre_start = 0;
        int pre_end = pre.length - 1;
        int post_start = 0;
        int post_end = post.length - 1;
        return helper(pre, pre_start, pre_end, post, post_start, post_end);
    }

    public TreeNode helper(int[] pre, int pre_start, int pre_end, int[] post, int post_start, int post_end) {
        if (pre_start > pre_end || post_start > post_end) return null;
        if (pre_start == pre_end) return new TreeNode(pre[pre_start]); // 因为后面有加1操作。
        TreeNode root = new TreeNode(pre[pre_start]);
        int target = pre[pre_start + 1];
        int idx = 0;
        for (int i = post_start; i <= post_end; i++) {
            if (post[i] == target) {
                idx = i;
                break;
            }
        }
        int diff = idx - post_start + 1;
        root.left = helper(pre, pre_start + 1, pre_start + diff, post, post_start, idx);
        root.right = helper(pre, pre_start + diff + 1, pre_end, post, idx + 1, post_end - 1);
        return root;
    }
}
