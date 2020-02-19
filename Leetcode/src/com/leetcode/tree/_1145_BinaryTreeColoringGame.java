package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _1145_BinaryTreeColoringGame {

//    主要看蓝色是在哪个部分。贪心。
    public int xLeft, xRight;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root == null) return false;
        xLeft = 0;
        xRight = 0;
        getNodes(root, x);
        int rest = n - xLeft - xRight - 1;
        // 除去x这个点 有三个部分，其中一个部分的个数大于 n/2的话就会保证赢。
        return Math.max(xLeft, Math.max(xRight, rest)) > n / 2;
    }

    public int getNodes (TreeNode root, int x) {
        if (root == null) return 0;
        int left = getNodes(root.left, x);
        int right = getNodes(root.right, x);
        if (root.val == x) {
            xLeft = left;
            xRight = right;
        }
        return left + right + 1;
    }
}
