package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import java.util.HashMap;

/**
 * @Date: 07/18/2020
 * @Description: 时间顺序DP
 **/
public class _337_HouseRobberIII {

    // 解释 https://www.cnblogs.com/grandyang/p/5275096.html
    // DP + Memo.
    // 当前层被选定，下一层肯定不能用，所以每次这样比较即可
    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        HashMap<TreeNode, Integer> map = new HashMap<>();
        return build(root, map);
    }

    // time:O(n) space:O(n)
    public static int build(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (map.get(root) != null) {
            return map.get(root);
        }
        if (root == null) {
            return 0;
        }
        int sumOfChose = 0;
        if (root.left != null) {
            sumOfChose += build(root.left.left, map) + build(root.left.right, map);
        }
        if (root.right != null) {
            sumOfChose += build(root.right.left, map) + build(root.right.right, map);
        }
        sumOfChose += root.val;
        int sumOfNotChose = build(root.left, map) + build(root.right, map);
        int res = Math.max(sumOfChose, sumOfNotChose);
        map.put(root, res);
        return res;
    }

    // 利用二维数组 第0位代表不偷，第一位代表偷。
    // https://www.youtube.com/watch?v=-i2BFAU25Zk
    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = build(root);
        return Math.max(res[0], res[1]);
    }

    public int[] build(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] res = new int[2];
        int[] left = build(root.left);
        int[] right = build(root.right);
        // 当前节点不偷的话
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 当前节点偷的话
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
