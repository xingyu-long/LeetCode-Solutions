package com.leetcode.bfs_and_dfs;

import java.util.Arrays;
import java.util.Collections;

public class _473_MatchstickstoSquare {

    /**
     * 473. Matchsticks to Square
     * When:2019/7/24
     * review1:11/1/2019
     * Difficulty: Medium
     *
     * @param nums
     * @return
     */
    // time:O(4 ^ n) space:O(n)
    public static boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) return false;
        Arrays.sort(nums);
        reverse(nums);
        return dfs(nums, new int[4], 0, sum / 4);
    }

    public static boolean dfs(int[] nums, int[] buckets, int index, int target) {
        if (index == nums.length) {
            if (buckets[0] == target && buckets[1] == target
                    && buckets[2] == target && buckets[3] == target) return true;
            return false;
        }
        for (int i = 0; i < 4; i++) { // 对每一个bucket进行尝试。
            if (buckets[i] + nums[index] > target) continue;
            buckets[i] += nums[index];
            if (dfs(nums, buckets, index + 1, target)) return true;
            buckets[i] -= nums[index];
        }
        return false;
    }

    public static void reverse(int[] nums) {
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

    public static void main(String[] args) {
        makesquare(new int[]{1, 2, 3, 4, 5, 5});
    }
}
