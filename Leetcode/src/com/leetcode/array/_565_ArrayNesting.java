package com.leetcode.array;

import java.util.HashSet;

/**
 * @Date: 09/03/2020
 * @Description: Set, cycle
 **/
public class _565_ArrayNesting {
    // time:O(n) space:O(n)
    public int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        HashSet<Integer> visited = new HashSet<>();
        // 因为每个数只会属于一个cycle里面，不会重复访问。这个之前没有考虑到
        for (int num : nums) {
            int count = 0;
            while (!visited.contains(num)) {
                visited.add(num);
                num = nums[num];
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
