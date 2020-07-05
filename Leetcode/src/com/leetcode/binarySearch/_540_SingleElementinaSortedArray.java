package com.leetcode.binarySearch;

/**
 * @Date: 05/12/2020
 * @Description: Binary Search
 **/
public class _540_SingleElementinaSortedArray {

    // 这种binary search没有那么明显
    // https://leetcode.com/problems/single-element-in-a-sorted-array/discuss/100733/Java-Binary-Search-with-Detailed-Explanation
    // https://leetcode.com/problems/single-element-in-a-sorted-array/discuss/627786/C%2B%2B-O(log-n)-time-O(1)-space-or-Simple-and-clean-or-Use-xor-to-keep-track-of-odd-even-pair
    // 后面再看这个，用到了另外一个模板。
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
