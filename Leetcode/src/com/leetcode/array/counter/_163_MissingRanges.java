/*
 * @Date: 08/11/2020 16:07:14
 * @LastEditTime: 06/05/2022 13:27:43
 * @Description: Simulation
 */
package com.leetcode.array.counter;

import java.util.ArrayList;
import java.util.List;


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

    // time: O(n), space:O(n)
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
        // 这时候的curr已经是next value，因为最后一步， curr++才能够破这个while循环
        // check the upper bound;
        if (upper - curr == 0) {
            res.add("" + upper);
        } else if (upper - curr > 0) {
            res.add(curr + "->" + upper);
        }
        return res;
    }
}
