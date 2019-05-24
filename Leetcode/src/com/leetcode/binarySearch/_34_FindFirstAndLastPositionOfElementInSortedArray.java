package com.leetcode.binarySearch;

public class _34_FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * When: 2019/05/24
     *
     * solution:
     * 写法与前面没有区别，主要是nums[mid] < target中的 '<' 或者 '>' 的方向，这样计算出的顺序也不一样
     * Given an array of integers sorted in ascending order, find the starting and ending
     * position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * For example,
     * Given [5, 7, 7, 8, 8, 10] and target value 8,
     * return [3, 4].
     *
     * findFirst -> 运行到 r = 3, l = 2的时候 然后返回的就是nums[3]
     * findLast -> 运行到  r = 5, l = 4的时候 然后返回的就是num[4]
     * @param nums
     * @param target
     * @return
     */

    //time: log(n) space: O(1)
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int start = findFirst(nums, target);
        if (start == -1) return new int[]{-1, -1};
        int last = findLast(nums, target);
        if (last == -1) return new int[]{-1, -1};
        return new int[]{start, last};
    }

    public int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    public int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[right] == target) return right;
        if (nums[left] == target) return left;
        return -1;
    }
}
