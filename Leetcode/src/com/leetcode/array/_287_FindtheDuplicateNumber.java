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
}
