package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 06/24/2020
 * @Description: Divide and Conquer
 **/
public class _95_UniqueBinarySearchTreesII {

    public static List<TreeNode> generateTrees2(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return genTreeList(1, n);
    }

    // https://www.youtube.com/watch?v=hQn61BjdA7M
    public static List<TreeNode> genTreeList(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) { // base case. 这个比较重要
            list.add(null);
        }
        for (int idx = start; idx <= end; idx++) {
            List<TreeNode> leftList = genTreeList(start, idx - 1); // 注意这里的start，是推导的关键。
            List<TreeNode> rightList = genTreeList(idx + 1, end);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(idx);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
