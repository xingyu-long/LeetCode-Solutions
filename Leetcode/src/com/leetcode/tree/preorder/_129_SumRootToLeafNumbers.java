package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        // 不能先写 sum = 10 * sum + root.val; 会导致计算异常 因为左右子树的计算顺序不同
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


    //BFS 相当于每次直接改变左右的值！
    public static int sumNumbers3(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // 注意这里是cur.val!
            if (cur.left == null && cur.right == null) {
                res += cur.val;
            }
            if (cur.left != null) {
                cur.left.val =  10 * cur.val + cur.left.val;
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                cur.right.val =  10 * cur.val + cur.right.val;
                queue.offer(cur.right);
            }
        }
        return res;
    }
    //BFS不修改原来的值 使用list记录
    public static int sumNumbers4(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        queue.offer(root);
        sumQueue.offer(root.val);
        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curSum = sumQueue.poll();
            // 这个操作很关键！
            if(cur.left == null && cur.right == null) res += curSum;
            if (cur.left != null) {
                sumQueue.offer(curSum * 10 + cur.left.val);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                sumQueue.offer(curSum * 10 + cur.right.val);
                queue.offer(cur.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);

        TreeNode leftOne = new TreeNode(9);
        TreeNode rightOne = new TreeNode(0);

        root.left = leftOne;
        root.right = rightOne;
//
        leftOne.left = new TreeNode(5);
        leftOne.right = new TreeNode(1);
        System.out.println(sumNumbers4(root));
    }
}
