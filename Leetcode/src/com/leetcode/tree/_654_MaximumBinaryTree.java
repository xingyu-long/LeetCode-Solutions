package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _654_MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helperWithSplit(nums, 0, nums.length - 1);
    }

    public TreeNode helperWithSplit(int[] nums, int start, int end) {
        if (start > end) return null;
        int maxIndex= start;
        for (int i = start; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = helperWithSplit(nums, start, maxIndex - 1);
        root.right = helperWithSplit(nums, maxIndex + 1, end);
        return root;
    }
}
