package com.leetcode.greedy;

/**
 * @Date: 07/26/2020
 * @Description: Greedy
 **/
public class _1526_MinimumNumberofIncrementsonSubarraystoFormaTargetArray {
    // 其实还不是很懂这个。。
    public int minNumberOperations(int[] target) {
        int res = 0;
        if (target == null || target.length == 0) return res;
        int prev = 0;
        for (int num : target) {
            res += Math.max(0, num - prev);
            prev = num;
        }
        return res;
    }
}
