package com.leetcode.binarySearch;

public class _35_SearchInsertPosition {

    /**
     * 35. Search Insert Position
     * When: 2019/05/22
     * Review1: 2019/7/17
     * review2:2019/10/14
     * <p>
     * solution：使用二分查找法，最后return left即可
     *
     * @param nums
     * @param target
     * @return
     */
    // time: O(logn) space: O(1)
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 另外一种方法，更加容易理解，分情况讨论
    public int searchInsert2(int[] nums, int target) {
        int res = -1;
        int left = 0;
        int right = nums.length - 1;
        while (res == -1) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                res = mid;
            } else if (target < nums[mid]) {
                if (mid == 0 || target > nums[mid - 1]) {
                    res = mid;
                } // eg: [1, 3, 5, 6] target = 2
                right = mid - 1;
            } else {
                // target > nums[mid]
                if (mid == nums.length - 1 || target < nums[mid + 1]) {
                    res = mid + 1;
                } // eg: [1, 3, 5, 6] target = 4
                left = mid + 1;
            }
        }
        return res;
    }

    // template写法 找到 >= 的
    public int searchInsert3(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}