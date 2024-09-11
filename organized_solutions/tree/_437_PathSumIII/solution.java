package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _437_PathSumIII {

    // 没有考虑到可能会有-2,1 满足 然后-2,1,-1,1也满足的情况
    // time:O(n^2) space:O(n)
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int currentVal = build(root, sum);
        int left = pathSum(root.left, sum); // 以当前的左子树为起点递归
        int right = pathSum(root.right, sum);
        return currentVal + left + right;
    }
    
    public int build(TreeNode root, int sum) {
        int res = 0;
        if (root == null) return 0;
        if (sum == root.val) res++; // * (这里很重要！) 因为后面可能会有多的情况，因为这里可以加上负数，也会导致0.
        int left = build(root.left, sum - root.val); 
        int right = build(root.right, sum - root.val);
        res += left + right;
        return res;
    }

    // 可以用prefix sum进行优化。记得看那个sub array count跟这个差不多
    // time:O(n) space:O(n)
    Map<Integer, Integer> map;
    int res;
    public int pathSum2(TreeNode root, int sum) {
        map = new HashMap<>();
        map.put(0, 1);
        res = 0;
        dfs(root, sum, 0);
        return res;
    }

    public void dfs(TreeNode root, int sum, int total) {
        if (root == null) {
            return;
        }

        total += root.val;
        if (map.containsKey(total - sum)) {
            res += map.get(total - sum);
        }
        map.put(total, map.getOrDefault(total, 0) + 1);
        dfs(root.left, sum, total);
        dfs(root.right, sum, total);
        // 相当于除去当前这层出现的累计和。。
        map.put(total, map.get(total) - 1);
    }
}