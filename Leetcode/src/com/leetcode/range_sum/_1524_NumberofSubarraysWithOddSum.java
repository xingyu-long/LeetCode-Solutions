package com.leetcode.range_sum;

/**
 * @Date: 07/26/2020
 * @Description: Prefix Sum
 **/
public class _1524_NumberofSubarraysWithOddSum {

    // 利用前缀数组来求值
    // 如果当前和为even，我们就看前面为odd的情况。
    // 相反也成立
    public int numOfSubarrays(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int MOD = (int) Math.pow(10, 9) + 7;
        int odd = 0, even = 1;
        int sum = 0, res = 0;
        for (int num : arr) {
            sum += num;
            if (sum % 2 == 0) {
                even++;
                res = (res + odd) % MOD;
            } else {
                odd++;
                res = (res + even) % MOD;
            }
        }
        return res;
    }
}
