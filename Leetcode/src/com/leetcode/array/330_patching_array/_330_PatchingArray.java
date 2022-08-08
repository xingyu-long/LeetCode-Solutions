package com.leetcode.array;

/**
 * @Date: 2019/8/3, 08/29/2020
 * @Description: Greedy
 **/
public class _330_PatchingArray {
    // https://www.cnblogs.com/grandyang/p/5165821.html
    // 利用 miss来记录可以到达的区间为 [1, miss) 加入一个数的时候 其miss就会 miss + num
    // time:O(n) space:O(1)
    public int minPatches(int[] nums, int n) {
        int i = 0, res = 0;
        long miss = 1;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                res++;
            }
        }
        return res;
    }
}