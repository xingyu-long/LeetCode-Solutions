package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _257_BinaryTreePaths {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    /**
     * 257. Binary Tree Paths
     * When: 2019/04/16
     *
     * solution:
     *  利用先序遍历，只是需要注意什么时候添加其字符串。
     *  相当于stack 所以string的前面例如前面几层就会保留下来 例如左边进去的时候是 1-> 那就在计算右边的时候继续保留
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        helper(res, "", root);
        return res;
    }

    public static void helper(List<String> res, String s, TreeNode root) {
        if (root == null) return;
        s += Integer.toString(root.val);
        if (root.left == null && root.right == null) {
            res.add(s);
            // 这里的string 初始化问题呢？ A: 进当前stack之前的s 是跟这里的不一样
            return;
        }
        s += "->";
        helper(res, s, root.left);
        helper(res, s, root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(2);
        TreeNode right = root.right;
        right.left = new TreeNode(3);
        right.right = new TreeNode(4);
        System.out.println(binaryTreePaths(root).toString());
    }
}
