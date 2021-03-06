/*
 * @Date: 2019-11-13 16:28:33
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-21 16:55:43
 */
package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.*;

public class _112_PathSum {
    //time:O(n) space:O(n)
    // 直接计算出
    public static boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) return false; //这里是false！ 很重要
        // 这里肯定是先减去之后再在跟着验证是否找到。
        sum -= root.val;
        // 满足这个的情况下
        if (sum == 0 && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    //BFS
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null && sum >= 0) return false;
        // 利用stack 先序遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) { // 减的话不能达到保存结果的情况
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null) {
                if (sum == node.val) {
                    return true;
                }
            }
            if (node.right != null) {
                stack.push(node.right);
                node.right.val += node.val;
            }
            if (node.left != null) {
                stack.push(node.left);
                node.left.val += node.val;
            }
        }
        return false;
    }

    // 上面的修改原本的值，这样不好。
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root == null) return false;
        HashMap<TreeNode, Integer> map = new HashMap<>();
        map.put(root, root.val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (map.get(node) == sum &&
                    node.left == null && node.right == null) return true;
            if (node.left != null) {
                queue.offer(node.left);
                map.put(node.left, map.get(node) + node.left.val);
            }
            if (node.right != null) {
                queue.offer(node.right);
                map.put(node.right, map.get(node) + node.right.val);
            }
        }
        return false;
    }
}
