package com.leetcode.binarySearch;

/**
 * @Date: 05/12/2020, 10/20/2020
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

    public int singleNonDuplicate2(int[] nums) {
        // XOR
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int rest = n - 1 - mid;
            if  (nums[mid] == nums[mid + 1]) {
                if (rest % 2 == 1) right = mid;
                else left = mid;
            } else {
                if (rest % 2 == 1) left = mid;
                else right = mid;
            }
        }
        // check two sides;
        if (left == 0 && nums[left] != nums[right]) return nums[left];
        if (right == n - 1 && nums[left] != nums[right]) return nums[right];
        if (nums[left] != nums[right] && nums[left] == nums[left - 1]) return nums[right];
        return nums[left];
    }
}
