package com.leetcode;

public class _129_SumRootToLeafNumbers {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 129. Sum Root to Leaf Numbers
     * When: 2019/04/17
     *
     * solution:
     * 利用res = res * 10 + x来进位
     * 用cur 表示当前值 以及sum 全部和
     *
     * Q: 传参改编其值吗？ 例如int类型 A: 用数组的方式
     * Q: 整型int 输入进来，这样向上的时候那个值 他有保留 而不是一直不变 （相比数组那样
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        if (root == null) return 0;
//        int[] res = new int[]{0, 0}; // 分别表示其res 和当前cur值
        int[] res = new int[]{0};
        helper(root, res, 0);
        return res[0];
    }

    public static void helper(TreeNode root, int[] res, int cur) {
        if (root == null) return;
        cur = cur * 10 + root.val;
        if (root.left == null && root.right == null) {
            res[0] += cur;
        }
        helper(root.left, res, cur);
        helper(root.right, res, cur);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        TreeNode leftOne = new TreeNode(2);
        TreeNode rightOne = new TreeNode(3);

        root.left = leftOne;
        root.right = rightOne;
        System.out.println(sumNumbers(root));
    }
}
