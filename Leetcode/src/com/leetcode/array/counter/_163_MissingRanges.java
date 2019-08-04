package com.leetcode.array.counter;

import java.util.ArrayList;
import java.util.List;

public class _163_MissingRanges {
    /**
     *  163. Missing Ranges
     *  When:2019/8/4
     *  Difficulty: Medium


         Given a sorted integer array where the range of elements are in the inclusive range [lower, upper],
         return its missing ranges.

         For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
        solution: 一直去找缺失的部分
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long alower = (long) lower, aupper = (long) upper;
        for (int num : nums) {
            if (num == alower) {
                alower++;
            } else if (alower < num) {
                if (alower + 1 == num) {
                    //刚好差这一个元素
                    res.add(String.valueOf(alower));
                } else {
                    res.add(alower + "->" + (num - 1));
                }
                alower = (long) num + 1;//这一步很重要
            }
        }
        //与upper比较
        if (alower == aupper) {
            res.add(String.valueOf(alower));
        } else if (alower < aupper) {
            res.add(alower + "->" + aupper);
        }
        return res;
    }
}
