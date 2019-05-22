package com.leetcode.binarySearch;

public class _35_SearchInsertPosition {

    /**
     * 35. Search Insert Position
     * When: 2019/05/22
     *
     * solution：使用二分查找法，最后return left即可
     * @param nums
     * @param target
     * @return
     */
    // time: O(logn) space: O(1)
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
