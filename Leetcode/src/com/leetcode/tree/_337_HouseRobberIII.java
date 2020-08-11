/*
 * @Date: 2019-11-14 14:37:26
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 15:27:13
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import java.util.HashMap;

public class _337_HouseRobberIII {
    // 解释 https://www.cnblogs.com/grandyang/p/5275096.html
    // DP + Memo.
    // 当前层被选定，下一层肯定不能用，所以每次这样比较即可
    public static int rob(TreeNode root) {
        if (root == null) return 0;
        HashMap<TreeNode, Integer> map = new HashMap<>();
        return helper(root, map);
    }
    // time:O(n) space:O(n)
    public static int helper(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (map.get(root) != null) return map.get(root);
        if (root == null) return 0;
        int sumOfChose = 0;
        if (root.left != null) {
            sumOfChose += helper(root.left.left, map) + helper(root.left.right, map);
        }
        if (root.right != null) {
            sumOfChose += helper(root.right.left, map) + helper(root.right.right, map);
        }
        sumOfChose += root.val;
        int sumOfNotChose = helper(root.left, map) + helper(root.right, map);
        int res = Math.max(sumOfChose, sumOfNotChose);
        map.put(root, res);
        return res;
    }
    // 利用二维数组 第0位代表不偷，第一位代表偷。
    // https://www.youtube.com/watch?v=-i2BFAU25Zk
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    public int[] helper(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] res = new int[2];
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        // 当前节点不偷的话
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 当前节点偷的话
        res[1] = root.val + left[0] + right[0];
        
        return res;
    }
}
