package com.leetcode.bfsANDdfs;

import java.util.Arrays;

public class _698_PartitionToKEqualSumSubsets {
    // 和473一样的思路
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Arrays.sort(nums);
        reverse(nums);
        if (sum % k != 0) return false;
        return dfs(nums, k, new int[k], sum / k, 0);
    }

    public boolean dfs(int[] nums, int k, int[] buckets, int target, int index) {
        if (index == nums.length) {
            for (int bucket : buckets) {
                if (bucket != target) return false;
            }
            return true;
        }
        for (int i = 0; i < k; i++) {
            if (buckets[i] + nums[index] > target) continue;
            buckets[i] += nums[index];
            if (dfs(nums, k, buckets, target, index + 1)) return true;
            buckets[i] -= nums[index];
        }
        return false;
    }

    public void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
