package com.leetcode.binarySearch;

public class _153_FindMinimumInRotatedSortedArray {

    /**
     *  153. Find Minimum in Rotated Sorted Array
     *  When: 2019/05/23
     *  Review1: 2019/7/19
     *
     * solution:
     * 标准的二分查找，但是需要找到inflection那个点
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/solution/
     * @param nums
     * @return
     */
    // time: O(logN) space:O(1)
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;

        // if there is no rotation, then the array is sorted
        if (nums[right] > nums[left]) {
            return nums[left];
        }

        while (left <= right) {
            // prevent overflow
            int mid = left + (right - left) / 2;

            // 使用mid 来前后来寻找inflection point
            //         left mid   right
            // eg: 4, 5, 6, 7, 2, 3
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            //  left   mid    right
            //eg: 4, 5, 1, 2, 3
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            // it shows that the inflection point would be on the right side
            // 与第一个元素比较，看是否sorted
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // time: O(logn) space:O(1)
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] < nums[end]) return nums[start];
        else return nums[end];
    }
}
