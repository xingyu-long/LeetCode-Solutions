package com.leetcode.binarySearch;

public class _33_SearchInRotatedSortedArray {


    /**
     * 33. Search in Rotated Sorted Array
     * When: 2019/05/22
     * Review1:2019/7/18
     * review2:2019/10/14
     * review3:11/6/2019
     * solution:
     * 首先找到最小的点，然后判断target在哪个部分里面
     *
     * @param nums
     * @param target
     * @return
     */

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 找到第一个小于nums[right]的情况
            // nums[right]因为翻转导致小于mid。。。所以我们将boundary一直向左移动
            if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // return left; // 找到最小的那个点

        int start = left;
        left = 0;
        right = nums.length - 1;
        // 看target是在哪一段，然后更改上下界。
        if (target >= nums[start] && target <= nums[right]) {
            left = start;
        } else {
            right = start;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] != target) return -1;
        else return left;
        // return -1;
    }


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
