package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _129_SumRootToLeafNumbers {

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
    // dfs
    public static int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public static int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return sum * 10 + root.val;
        }
        // 不能先写 sum = 10 * sum + root.val; 会导致计算异常
        int left = helper(root.left, 10 * sum + root.val);
        int right = helper(root.right, 10 * sum + root.val);
        return left + right;
    }

    // 先保存每条路径的string 然后相加
    public static int sumNumbers2(TreeNode root) {
        //dfs bfs均可以
        //用List<String>
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        List<String> strs = new ArrayList<>();
        helper(root, strs, "");
        int sum  = 0;
        for (String str : strs) {
            sum += Integer.parseInt(str);
        }
        return sum;
    }

    public static void helper(TreeNode root, List<String> strs, String s) {
        if (root == null) return;
        s += Integer.toString(root.val);
        if (root.left == null && root.right == null) {
            strs.add(s);
        }
        helper(root.left, strs, s);
        helper(root.right, strs, s);
    }


    //BFS


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);

        TreeNode leftOne = new TreeNode(9);
        TreeNode rightOne = new TreeNode(0);

        root.left = leftOne;
        root.right = rightOne;

        leftOne.left = new TreeNode(5);
        leftOne.right = new TreeNode(1);
        System.out.println(sumNumbers2(root));
    }
}
