package com.leetcode.binary_search;

public class _162_FindPeakElement {

    /**
     * 162. Find Peak Element
     * When: 2019/05/24
     * Review1: 2019/7/20
     * review2:2019/10/15
     * @param nums
     * @return
     */
    // time: O(logn) space: O(1)
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1; // 注意这里的 - 1操作
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // nums[mid - 1] 与 nums[mid]比较 然后确定是在递增还是递减序列上
    public int findPeakElement2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid - 1] > nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] > nums[right]) return left;
        else return right;
    }
}
