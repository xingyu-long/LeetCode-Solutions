/*
 * @Date: 07/18/2022 21:39:33
 * @LastEditTime: 07/18/2022 21:39:40
 * @Description: Greedy, Math
 */
package com.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

public class _2178_MaximumSplitofPositiveEvenIntegers {
    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) {
            return new ArrayList<>();
        }
        List<Long> res = new ArrayList<>();
        long sum = 0;
        long i = 2;
        while (sum + i <= finalSum) {
            res.add(i);
            sum += i;
            i += 2;
        }
        int size = res.size();
        res.set(size - 1, res.get(size - 1) + (finalSum - sum));
        return res;
    }
}
