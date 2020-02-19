package com.leetcode.math;

import java.util.Arrays;

public class _453_MinimumMovestoEqualArrayElements {
    // time:O(nlogn)
    public int minMoves(int[] nums) {
        // 从brute force 出发，其主要思想就是找到min和max，然后增加非max的部分+1
        // 优化一点点 增加非max部分 max-min这么大的值
        // 利用排序，然后依然使用max - min的思路。
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            res += nums[i] - nums[0];
        }
        return res;
    }

    // 还有纯数学的实现
}
