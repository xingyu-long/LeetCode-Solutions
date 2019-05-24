package com.leetcode.binarySearch;

public class _162_FindPeakElement {

    /**
     * 162. Find Peak Element
     * When: 2019/05/24
     *
     * solution:
     * 因为这里只需要找一个peak点就行了
     * 分两种情况
     * (1) 如果 nums[m-1] < nums[m] < nums[m + 1] 则nums[m]这个就是peak点
     * (2) 如果 nums[m-1] > nums[m] 或者 nums[m] < nums[m + 1]
     *      (2.1) 如果0到m-1 是升序的话 nums[m - 1]即是peak点
     *      (2.2) ...........是降序的话 则nums[0]是peak点
     *      (2.3 & 2.4) nums[m+1]类似思路
     * @param nums
     * @return
     */
    // time: O(logn) space: O(1)
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
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
}
