/*
 * @Date: 12/14/2020 09:38:20
 * @LastEditTime: 12/14/2020 09:52:16
 * @Description: Math
 */
package com.leetcode.math;

public class _507_PerfectNumber {
    // time: O(n)
    public boolean checkPerfectNumber(int num) {
        int temp = num / 2;
        int remain = num;
        for (int i = temp; i > 0; i--) {
            if (num % i == 0) {
                remain -= i;
                if (remain == 0)
                    return true;
                if (remain < 0)
                    return false;
            }
        }
        return false;
    }

    // Time: O(Sqrt(n))
    public boolean checkPerfectNumber2(int num) {
        if (num == 1) return false;
        int sum = 1;
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) sum += num / i;
            }
        }
        return sum == num;
    }
}
