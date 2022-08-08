package com.leetcode.binary_search;

public class _154_FindMinimuminRotatedSortedArrayII {
    
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
