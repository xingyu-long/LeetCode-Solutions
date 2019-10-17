package com.leetcode.binarySearch;

public class _34_FindFirstAndLastPositionOfElementInSortedArray {

    /**
     *  34. Find First and Last Position of Element in Sorted Array
     *  When: 2019/05/24
     *  Review1: 2019/7/18
     *
     *  solution:
     *  主要考虑到等于mid的时候，target可能存在的位置
     * @param nums
     * @param target
     * @return
     */
    // time: O(logN) space:O(1)
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1, -1};
        int one = findFirst(nums, target);
        int two = findLast(nums, target);
        return new int[]{one, two};
    }

    // 找到第一个等于或者大于target的位置。
    public int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // System.out.println("first: left = " + left + ", right = " + right);
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    // 找到第一个大于target的位置，肯定会有偏移，但这个只有1。
    public int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // System.out.println("last: left = " + left + ", right = " + right);
        if (nums[right] == target) return right;
        if (nums[left] == target) return left;
        return -1;
    }
}
