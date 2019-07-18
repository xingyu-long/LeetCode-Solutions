package com.leetcode.binarySearch;

public class _33_SearchInRotatedSortedArray {


    /**
     *  33. Search in Rotated Sorted Array
     *  When: 2019/05/22
     *  Review1:2019/7/18
     *
     * solution:
     * 主要还是二分法，只是需要检查哪一半是有序的
     * 需要好好记忆
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // prevent overflow
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            // 表示如果前半部分 有序的话
            // 走到这里就已经表示取不到mid这个情况，只能向前或者向后
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 表示如果后半部分 有序的话
            if (nums[mid] <= nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
