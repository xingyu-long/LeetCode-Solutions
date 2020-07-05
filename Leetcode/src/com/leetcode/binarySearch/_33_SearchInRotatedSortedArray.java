package com.leetcode.binarySearch;
/**
 * @Date: 2019/05/22, 2019/7/18, 2019/10/14, 11/6/2019
 *          04/13/2020
 * @Description: Binary Search, Rotated
 **/
public class _33_SearchInRotatedSortedArray {
    /**
     * 因为是折点
     * 所以右边的那部分有序的应该最大值就要小于left 可以画图看
     *      /
     *     /
     *    /
     *   left ------  / right
     *               /
     *              /
     *             /
     *            /
     * 所以如果mid在左边的话，就需要nums[mid] > nums[left]
     * 否则就是右边了
     * @param nums
     * @param target
     * @return
     */
    // 新的 left + 1 < right模板
    // https://www.youtube.com/watch?v=eG27FBcmy1k
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // [a, b]; 两边都是闭区间
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // if (nums[mid] == target) return mid;
            if (nums[mid] > nums[left]) { // 说明left边有序
                if (target >= nums[left] && target <= nums[mid]) right = mid;
                else left = mid;
            } else if (nums[mid] < nums[left]){ // right 有序
                if (target >= nums[mid] && target <= nums[right]) left = mid;
                else right = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    // 也可以把条件写成与右边比较
    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // [a, b]; 两边都是闭区间
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // if (nums[mid] == target) return mid;
            if (nums[mid] > nums[right]) { // 说明left边有序
                if (target >= nums[left] && target <= nums[mid]) right = mid;
                else left = mid;
            } else if (nums[mid] < nums[right]){ // right 有序
                if (target >= nums[mid] && target <= nums[right]) left = mid;
                else right = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
