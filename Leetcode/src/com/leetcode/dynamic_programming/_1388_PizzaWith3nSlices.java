/*
 * @Date: 2020-03-24 15:36:40
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-25 11:24:50
 */
package com.leetcode.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class _1388_PizzaWith3nSlices {
    public int maxSizeSlices(int[] slices) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(slices, 0, slices.length - 1, slices.length / 3, 1, memo);
    }
    
    public int dfs(int[] slices, int start, int end, int n, int cycle, Map<String, Integer> memo) {
        String key = start + "-" + end + "-" + n + "-" + cycle;
        if (memo.get(key) != null) 
            return memo.get(key);
        if (n == 1) { // 只剩一个元素，则只需要遍历元素找最大的一个即可
            int max = Integer.MIN_VALUE;
            for (int i = start; i <= end; i++) {
                max = Math.max(max, slices[i]);
            }
            memo.put(key, max);
            return max;
        }
        
        if (end - start + 1 < 2 * n - 1) 
            return Integer.MIN_VALUE;
        
        int res = Math.max(dfs(slices, start + cycle, end - 2, n - 1, 0, memo) + slices[end],
                          dfs(slices, start, end - 1, n, 0, memo));
        memo.put(key, res);
        return res;
    }
    
    public static void main(String[] args) {
        _1388_PizzaWith3nSlices slice = new _1388_PizzaWith3nSlices();
        int[] nums = {1,2,3,4,5,6,7,8,9};
        slice.maxSizeSlices(nums);
    }
}