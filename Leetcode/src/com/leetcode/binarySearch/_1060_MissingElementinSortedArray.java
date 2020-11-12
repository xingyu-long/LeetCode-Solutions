package com.leetcode.binarySearch;

/**
 * @Date: 05/24/2020
 * @Description: binary search
 **/
public class _1060_MissingElementinSortedArray {
    // 类似于模拟解法
    public int missingElement(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int curr = nums[0];
        int n = nums.length;
        for (int num : nums) {
            if (curr == num) {
                curr += 1;
            } else {
                int diff = num - curr;
                if (diff < k) {
                    curr = num + 1;
                    k -= diff;
                } else {
                    return curr + k - 1;
                }
            }
        }
        return curr + k - 1;
    }
    // time:O(logN) space:O(1)
    public int missingElement2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        int missingCount = getMissingCount(nums, left, right);
        if (missingCount < k) {
            return nums[right] + k - missingCount;
        }

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int miss = getMissingCount(nums, left, mid);
            if (miss >= k) {
                right = mid;
            } else {
                left = mid;
                k -= miss;
            }
        }
        return nums[left] + k;
    }

    // 计算还需要多少数字
    private int getMissingCount(int[] nums, int i, int j) {
        return nums[j] - nums[i] - (j - i);
    }
}
