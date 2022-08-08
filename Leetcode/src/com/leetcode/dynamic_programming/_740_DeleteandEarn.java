package com.leetcode.dynamic_programming;

public class _740_DeleteandEarn {
    
    /**
     * When:03/16/2020
     * @param nums
     * @return
     */
    // 当时卡的地方，是把这个想做搜索，然后想去实际的remove
    // 但利用桶排序就转化成了house robber的问题
    // time: O(n) space:O(n)
    public int deleteAndEarn(int[] nums) {
        // 利用桶排序构造，之后按照无法取相邻的情况进行操作
        if (nums == null || nums.length == 0) return 0;
        int n = 10001;
        int[] values = new int[n];
        for (int num : nums) 
            values[num] += num;
        
        int skip = 0, take = 0;
        for (int i = 0; i < n; i++) {
            int temp = take;
            take = Math.max(take, skip + values[i]);
            skip = Math.max(skip, temp);
        }
        return Math.max(take, skip);
    }
}