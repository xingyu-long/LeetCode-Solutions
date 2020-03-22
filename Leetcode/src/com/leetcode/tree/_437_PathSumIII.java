/*
 * @Date: 2019-12-25 21:34:11
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-21 17:31:19
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _437_PathSumIII {

    // 没有考虑到可能会有-2,1 满足 然后-2,1,-1,1也满足的情况
    // time:O(n^2) space:O(n)
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int currentVal = helper(root, sum);
        int left = pathSum(root.left, sum); // 以当前的左子树为起点递归
        int right = pathSum(root.right, sum);
        return currentVal + left + right;
    }
    
    public int helper(TreeNode root, int sum) {
        int res = 0;
        if (root == null) return 0;
        if (sum == root.val) res++; // * (这里很重要！) 因为后面可能会有多的情况，因为这里可以加上负数，也会导致0.
        int left = helper(root.left, sum - root.val); 
        int right = helper(root.right, sum - root.val);
        res += left + right;
        return res;
    }

    // 可以用prefix sum进行优化。记得看那个sub array count跟这个差不多
    // time:O(n) space:O(n)
    public int pathSum2(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (root == null) return 0;
        map.put(0, 1); // 这个不能忘了
        return dfs(root, sum, 0, map);
    }
    
    public int dfs(TreeNode root, int target, int prefix, HashMap<Integer, Integer> map) {
        if (root == null) return 0;
        prefix += root.val;
        int res = 0;
        res += map.getOrDefault(prefix - target, 0);
        map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        int left = dfs(root.left, target, prefix, map);
        int right = dfs(root.right, target, prefix, map);
        res += left + right;
        map.put(prefix, map.get(prefix) - 1); 
        // 后序往上的时候，这个prefix就会减少一次。
        // 因为构成这样的和已经没了，这个和普通的arr不一样，因为深度不同，你和的值就会改变。
        return res;
    }
}