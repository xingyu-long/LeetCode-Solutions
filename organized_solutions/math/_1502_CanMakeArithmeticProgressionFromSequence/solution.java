package com.leetcode.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

/**
 * @Date: 07/15/2020
 * @Description: Math, sort
 **/
public class _1502_CanMakeArithmeticProgressionFromSequence {

    // 打比赛的时候没有想到O(n)的解法
    // time:O(nlogn)
    public boolean canMakeArithmeticProgression(int[] arr) {
        // 利用sort 如果能够达到这样的条件，那么sort之后应该也可以
        if (arr == null || arr.length < 2) {
            return false;
        }
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }

    // 利用最大最小值，然后计算平均差值。
    // time:O(n)
    public boolean canMakeArithmeticProgression2(int[] arr) {
        int min = arr[0], max = arr[0];
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int n = arr.length;
        if ((max - min) % (n - 1) != 0) return false;
        int diff = (max - min) / (n - 1);
        while (n > 0) {
            if (!set.contains(min)) {
                return false;
            }
            min += diff;
            n--;
        }
        return true;
    }
}
