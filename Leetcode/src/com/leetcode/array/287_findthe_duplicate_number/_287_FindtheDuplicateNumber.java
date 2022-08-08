package com.leetcode.array;

import java.util.HashSet;

public class _287_FindtheDuplicateNumber {

    /**
     *  287. Find the Duplicate Number
     *  When:2019/7/11
     *  Difficulty: Medium
     *
     *  Solution:
     *  (1) sort
     *  (2) set
     *  (3) fast & slow (like the Linked List Cycle II.)
     * @param nums
     * @return
     */
    //time: O(n) space:O(n)
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            if (!set.add(num)) {
                res = num;
                break;
            }
        }
        return res;
    }

    // slow & fast pointer
    public int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length > 1) {
            int slow = nums[0];
            int fast = nums[slow];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            slow = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }
        return - 1;
    }

    // 利用binary search
    // 从[1, n-1]开始搜索。判断条件需要好好斟酌，等于的时候应该让其搜索右半边（因为左半部分没有重复的出现的可能性了）
    // 检查也是从left检查，因为可能的情况是左边重复导致右边重复，但没有right重复了左边不重复的情况
    public int findDuplicate3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 1;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (count(nums, mid) > mid) right = mid;
            else left = mid;
        }
        if (count(nums, left) > left) return left;
        return right;
    }

    public int count(int[] nums, int target) {
        int res = 0;
        for (int num : nums) {
            if (num <= target) res++;
        }
        return res;
    }
}
