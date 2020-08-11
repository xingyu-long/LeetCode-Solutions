/*
 * @Date: 2020-03-21 18:10:17
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-21 18:10:31
 */
package com.leetcode.tree;

import java.util.HashMap;

public class _666_PathSumIV {
    public int res = 0;
    public int pathSum(int[] nums) {
        /*
        113, 215, 221
        20 + 2 * 1 - 1
        2 * x - 1 求left
        */
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num / 10, num % 10);
        }
        dfs(nums[0] / 10, map, 0);
        return res;
    }
    
    
    public void dfs(int num, HashMap<Integer, Integer> map, int sum) {
        int level = num / 10, pos = num % 10;
        int left = (level + 1) * 10 + 2 * pos - 1;
        int right = left + 1;
        sum += map.get(num);
        if (map.get(left) == null && map.get(right) == null) {
            res += sum;
            return;
        }
        if (map.get(left) != null) dfs(left, map, sum);
        if (map.get(right) != null) dfs(right, map, sum);
    }
    
}