package com.leetcode.binarySearch;

public class _154_FindMinimuminRotatedSortedArrayII {

    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * When: 2019/05/24
     *
     * solution:
     * 只是多了把右边的--操作
     *
     * ？ 这里为啥right 不用mid - 1
     *
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/discuss/48808/My-pretty-simple-code-to-solve-it
     * Q&A
     * Why hi = mid and not hi = mid - 1?
     * in the case of nums[mid] < nums[hi], it means that nums[mid] is the smallest on right side, it could be the smallest on the left side too, so we need to include it in the following search.
     * e.g. 4,5,6,7,0,1,2,3,4
     * mid is 0. If we use hi = mid - 1, 0 would be omitted.
     *
     * Why hi-- and return nums[lo]?
     * hi-- is to skip duplicated numbers
     * lo and hi will be the same in the end as that is the terminating condition.
     * so nums[lo] and nums[hi]
     * @param nums
     * @return
     */

    // time: O(logn) worst : O(n)
    // space: O(1)
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

            if (nums[mid] < nums[right]) {
                // 表示会出现做左边
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                //相等的时候，那就除去最后的值 这样没啥影响
                right--;
            }
        }
        return nums[left];
    }

    // 思路和之前的25，81一致，后者就是有需要看target的位置
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]){
                left = mid;
            } else right--;
        }
        return Math.min(nums[left], nums[right]);
    }
}
