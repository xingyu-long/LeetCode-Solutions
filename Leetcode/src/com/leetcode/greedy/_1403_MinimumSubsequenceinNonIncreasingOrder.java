package com.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1403_MinimumSubsequenceinNonIncreasingOrder {
    // 贪心，从最大的开始取就行
    public List<Integer> minSubsequence(int[] nums) {
        // [4,3,10,9,8]
        // 10,9,8,4,3    34
        if (nums == null || nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int sum = 0;
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (sum > total) break;
            res.add(nums[i]);
            sum += nums[i];
            total -= nums[i];
        }
        return res;
    }
}
