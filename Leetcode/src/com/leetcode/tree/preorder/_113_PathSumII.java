package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class _113_PathSumII {

    /**
     *  113. Path Sum II
     *  When: 2019/04/17
     *  Review1:2019/7/16
     *  Review2: 2019/7/26
     *  review3: 2019/9/12
     *  Difficulty: Medium
     *
     *  solution: 依然使用先序遍历（DFS）
     * 用sum 减去里面的要是等于 0 则表示找到了其中的一条路
     *
     *
     * Q:不是很懂这里的cur.remove(cur.size() - 1)操作   (A: 为了删除后面的 向上计算的时候 那个cur要保持当前层需要的样子)
     *
     * Q: 但是为什么前面的sum就没删除呢？（后面需要验证） A: 因为这里有栈，所保存了当前的值
     *
     * Q: 为什么new ArrayList<>(cur) 才可以用  A: 如果不是新的对象会导致其cur删除，因为remove操作影响cur这个对象（这个对象与res.add 是
     * 同一个对象，所以cur最后为空，即res也会为空）
     *
     * Test case:
     *     1         sum = 4;
     *    / \
     *   2  3
     *
     * (1) root = 1 -> cur[1, ]; sum = 3; 不满足添加res条件
     *
     * (1.1) 左边
     *        root = 2 -> cur[1, 2, ] sum = 1; 不满足res条件
     *        (1.1.1) 左边
     *              root = null -> return;
     *        (1.1.2) 右边
     *              root = null -> return;
     *        (1.1.3) remove cur的最后一个元素
     *             #（超级重要） cur = [1, ];
     * (1.2) 右边
     *        root = 3 -> [1, 3] sum = 0; 满足res条件 -> res[[1, 3] ];
     *        (1.2.1) 左边
     *              root = null -> return;
     *        (1.2.2) 右边
     *              root = null -> return;
     *        (1.2.3) remove
     *            # (超级重要) cur = [1, ]
     * (1.3) remove
     *        # (超级重要) cur = [];
     * @param root
     * @param sum
     * @return
     */
    //time: O(n) space:O(n)
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, new ArrayList<>(),root, sum);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur,TreeNode root, int sum) {
        if (root == null) return;
        cur.add(root.val);
        sum -= root.val; // 具有栈的特征，可以在每一层表示那一层的情况
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<>(cur)); // 这样才可以保留单独不同的list，否则会被当成同一个处理掉
            // 这里不用return，因为最后还需要把cur的最后一个元素移除
        }
        helper(res, cur, root.left, sum);
        helper(res, cur, root.right, sum);
        cur.remove(cur.size() - 1);
    }

    // BFS (类似于中序遍历)
    public static List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        TreeNode cur = root;
        TreeNode prev = null;// 表示当前cur前面的一个（在栈中的顺序）
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> path = new ArrayList<>();
        int totalSum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                path.add(cur.val);
                totalSum += cur.val;
                cur = cur.left;
            }
            cur = stack.peek();
            // 查找右边是否有节点
            if (cur.right != null && cur.right != prev) {
                cur = cur.right;
                continue;
            }
            // 判断当前节点是否为叶子节点
            if (cur.left == null && cur.right == null && totalSum == sum) {
                res.add(new ArrayList<>(path));
            }
            stack.pop();
            prev = cur;
            totalSum -= cur.val;
            path.remove(path.size() - 1);
            cur = null;
        }

        return res;
    }
}
