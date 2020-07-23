package com.leetcode.array.counter;

import java.util.ArrayList;
import java.util.List;

public class _228_SummaryRanges {

    /**
     *  228. Summary Ranges
     *  When: 2019/03/27
     *  Review1:2019/8/4
     * 12/2
     * solution:
     * 找寻一直连续的数字，主要的就是在while循环 一定要 i < nums.length - 1
     *
     * 涉及到的数据结构：
     * List<>
     *
     * @param nums
     * @return
     */
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
