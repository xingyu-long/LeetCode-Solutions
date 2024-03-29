package com.leetcode.binary_search;

/**
 * @Date: 2019/05/22, 2019/7/18, 2019/10/14 04/13/2020
 * @Description: Binary Search, Rotated
 **/
public class _81_SearchInRotatedSortedArrayII {

    // 相当于用right来判断。
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        // [a, b]; 两边都是闭区间
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // if (nums[mid] == target) return mid;
            if (nums[mid] > nums[right]) { // 说明left边有序
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else if (nums[mid] < nums[right]) { // right 有序
                if (target >= nums[mid] && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                right--;
            }
        }
        if (nums[left] == target) {
            return true;
        }
        if (nums[right] == target) {
            return true;
        }
        return false;
    }
}
