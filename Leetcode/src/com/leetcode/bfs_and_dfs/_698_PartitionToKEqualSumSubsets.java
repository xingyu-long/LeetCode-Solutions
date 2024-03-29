/*
 * @Date: 2019-11-01 15:41:38
 * 
 * @LastEditors: Please set LastEditors
 * 
 * @LastEditTime: 07/25/2022 16:29:22
 */
package com.leetcode.bfs_and_dfs;

import java.util.Arrays;

public class _698_PartitionToKEqualSumSubsets {
    // backtracking
    // 和473一样的思路，这个其实是在强搜
    // exponential O(k^(N−k) k!),
    // TLE
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Arrays.sort(nums);
        reverse(nums);
        if (sum % k != 0)
            return false;
        return dfs(nums, k, new int[k], sum / k, 0);
    }

    public boolean dfs(int[] nums, int k, int[] buckets, int target, int index) {
        if (index == nums.length) {
            for (int bucket : buckets) {
                if (bucket != target)
                    return false;
            }
            return true;
        }
        for (int i = 0; i < k; i++) {
            if (buckets[i] + nums[index] > target)
                continue;
            buckets[i] += nums[index];
            if (dfs(nums, k, buckets, target, index + 1))
                return true;
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


    // 从每个桶出发，然后遍历值。
    // time:(K  2^n)
    public boolean canPartitionKSubsets2(int[] nums, int k) {
        // 利用binary search X 应该这个不是求连续的
        if (nums == null || nums.length == 0) 
            return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) 
            return false;
        Arrays.sort(nums);
        // 原理一样，倒着找
        // 有点类似于Sudoku里面一个个搜索的感觉
        int n = nums.length;
        boolean[] visited = new boolean[n];
        return dfs(nums, visited, sum / k, n - 1, 0, k);
    }
    
    public boolean dfs(int[] nums, boolean[] visited, int target, int end, int sum, int k) {
        if (k == 0) 
            return true;
        // go to next bucket 依然从n - 1开始找，因为有的没有访问到
        if (sum == target && dfs(nums, visited, target, nums.length - 1, 0, k - 1)) {
            return true;
        }
        
        for (int i = end; i >= 0; i--) {
            if (visited[i]) continue;
            if (sum + nums[i] > target) continue;
            visited[i] = true;
            if (dfs(nums, visited, target, i - 1, sum + nums[i], k))
                return true;
            visited[i] = false;
        }
        return false;
    }
}
