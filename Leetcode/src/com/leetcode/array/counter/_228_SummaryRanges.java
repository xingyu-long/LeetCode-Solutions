package com.leetcode.array.counter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date:  2019/03/27, 2019/03/27, 08/04/2020
 * @Description: index以及index + 1
 **/
public class _228_SummaryRanges {

    // 更加简洁的写法。
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int index = 0, n = nums.length;
        List<String> res = new ArrayList<>();
        while (index < n) {
            if (index + 1 < n && nums[index] + 1 == nums[index + 1]) {
                int start = nums[index], end = nums[index];
                while (index + 1 < n && nums[index] + 1 == nums[index + 1]) {
                    end = nums[index + 1];
                    index++;
                }
                res.add(start + "->" + end);
            } else {
                res.add("" + nums[index]);
            }
            index++;
        }
        return res;
    }
}
