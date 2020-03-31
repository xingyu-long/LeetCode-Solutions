/*
 * @Date: 2020-03-30 14:33:57
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-30 14:50:03
 */
package com.leetcode.math;

public class _1390_FourDivisors {
    // 当时尝试用sqrt失败了
    // time: O(n * sqrt(num))
    public int sumFourDivisors(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        for (int num : nums) {
            res += sumDivisors(num);
        }
        return res;
    }
    
    public int sumDivisors(int num) {
        int count = 0;
        int sum = 0;
        for (int i = 1; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {// 能整除
                if (i * i != num) { // 说明两个不同的因数
                    count += 2;
                    sum += i;
                    sum += num / i;
                } else {
                    count += 1;
                    sum += i;
                }
            }
            if (count > 4) {
                return 0;
            }
        }
        return count == 4 ? sum : 0;
    }
}