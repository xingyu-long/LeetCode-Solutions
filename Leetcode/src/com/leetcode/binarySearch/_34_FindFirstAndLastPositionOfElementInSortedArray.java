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
    // 删除不好理解的情况
    // time: O(logn) space:O(1)
    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = findLeft(nums, target);
        int right = findRight(nums, target);
        if (left == -1 || right == -1) {
            return new int[]{-1, -1};
        } else {
            return new int[]{left, right};
        }
    }

    public int findLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int findRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == nums.length - 1 || nums[mid + 1] > target) {
                    return mid;
                }
                left = mid + 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
