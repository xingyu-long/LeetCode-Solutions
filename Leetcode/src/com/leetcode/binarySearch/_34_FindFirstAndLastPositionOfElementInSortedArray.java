package com.leetcode.binarySearch;

/**
 * @Date: 07/05/2020
 * @Description: Binary Search, left + 1 < right template.
 **/
public class _34_FindFirstAndLastPositionOfElementInSortedArray {
    // time: O(logN) space:O(1)
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int one = findFirst(nums, target);
        int two = findLast(nums, target);
        if (one != -1 && two != -1) {
            return new int[]{one, two};
        }
        return new int[]{-1, -1};
    }

    // 找到第一个等于或者大于target的位置。
    public int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    // 找到第一个大于target的位置，肯定会有偏移，但这个只有1。
    public int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[right] == target) {
            return right;
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }
}
