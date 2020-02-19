package com.leetcode.binarySearch;

public class _35_SearchInsertPosition {

    /**
     * 35. Search Insert Position
     * When: 2019/05/22
     * Review1: 2019/7/17
     * review2:2019/10/14
     * <p>
     * solution：使用二分查找法，最后return left即可
     *
     * @param nums
     * @param target
     * @return
     */
    // 需要判断是在left和right的左边还是右边或者中间
    public int searchInsert4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) right = mid;
            else left = mid;

        }
        // 三种可能性
        if (target > nums[right]) return right + 1;
        if (target <= nums[left]) return left;
        else return right;
    }
}