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
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        for (int i = 0; i < nums.length; i++){
            int num = nums[i];
            // 这里的nums.length - 1很重要！！！
            while (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) i++;
            if (num != nums[i]) res.add(num + "->" + nums[i]);
            else res.add(""+num);
        }
        return res;
    }

    public List<String> summaryRanges2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int n = nums.length;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = nums[i];
            int end = nums[i];
            while (i + 1 < n && end + 1 == nums[i + 1]) {
                end++;
                i++;
            }
            if (start == end) {
                res.add(String.valueOf(start)); // 表示没有移动。
            } else {
                res.add(String.valueOf(start) + "->" + String.valueOf(end));
            }
        }
        return res;
    }
}
