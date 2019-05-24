package com.leetcode.binarySearch;

public class _81_SearchInRotatedSortedArrayII {

    /**
     * 81. Search in Rotated Sorted Array II
     * When: 2019/05/22
     *
     * solution: 主要是要去除那些相同的元素。
     * 这里的[1] 0 这种情况
     * 是通过left++ 然后得到false
     * 而33题 是通过nums[left] <= nums[mid] 进去的。
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
//            int mid = (left + right) / 2;
            // prevent overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            if (nums[right] == nums[mid]) {
                right--;
                continue;
            }
            if (nums[left] < nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
