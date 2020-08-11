package com.leetcode.array.counter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 07/05/2020
 * @Description: simulation,
 **/
public class _163_MissingRanges {

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

    // 一步一步的计算，这个很重要！
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        long curr = lower;
        int index = 0;
        List<String> res = new ArrayList<>();
        while (curr <= upper && index < nums.length) {
            if (curr == nums[index]) {
                index++;
                curr += 1;
            } else {
                if (nums[index] - curr == 1) {
                    // one element;
                    res.add("" + curr);
                } else if (nums[index] - curr > 1) {
                    // store the range.
                    res.add(curr + "->" + (nums[index] - 1));
                }
                curr = nums[index];
            }
        }
        // check the upper bound;
        if (upper - curr == 0) {
            res.add("" + upper);
        } else if (upper - curr > 0) {
            res.add(curr + "->" + upper);
        }
        return res;
    }
}
