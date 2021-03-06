/*
 * @Date: 11/27/2019 17:28:29
 * @LastEditTime: 12/14/2020 10:00:17
 * @Description: Math, Digit(/10, %10)
 */
package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class _728_SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (canDivided(i))
                res.add(i);
        }
        return res;
    }

    public boolean canDivided(int num) {
        int temp = num;
        while (num != 0) {
            int digit = num % 10;
            if (digit == 0 || temp % digit != 0)
                return false;
            num /= 10;
        }
        return true;
    }
}
