package com.leetcode.tree;

import com.leetcode.common.MainClass;
import com.leetcode.common.TreeNode;

public class _230_KthSmallestElementInABST {


    /**
     * 230. Kth Smallest Element in a BST
     * When: 2019/04/25
     *
     * solution: BST中序遍历的时候则就是有序的（注意那个变量的变化）
     * time: O(log^N)
     * space: O(n) 因为递归
     * @param root
     * @param k
     * @return
     */
    public static int kthSmallest(TreeNode root, int k) {
        int[] res = new int[]{0, k}; // 第一个用来作为结果，第二个用计算k 这样k才会一直减
        helper(root, res);
        return res[0];
    }

    public static void helper(TreeNode root, int[] res) {
        if (root == null) return;

        helper(root.left, res);
        res[1]--;
        if (res[1] == 0) {
            res[0] = root.val;
        }
        helper(root.right, res);
    }

    public static void main(String[] args) {
        String s = "[3,1,4,null,2]";
        MainClass mainClass = new MainClass();
        TreeNode root = mainClass.stringToTreeNode(s);
        System.out.println(kthSmallest(root,1));
    }

}
