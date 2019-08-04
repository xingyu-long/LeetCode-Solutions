package com.leetcode.array.counter;

import java.util.ArrayList;
import java.util.List;

public class _228_SummaryRanges {

    /**
     *  228. Summary Ranges
     *  When: 2019/03/27
     *  Review1:2019/8/4
     *
     * solution:
     * 找寻一直连续的数字，主要的就是在while循环 一定要 i < nums.length - 1
     *
     * 涉及到的数据结构：
     * List<>
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        for (int i = 0; i < nums.length; i++){
            int num = nums[i];
            // 这里的nums.length - 1很重要！！！
            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) i++;
            if (num != nums[i]) res.add(num + "->" + nums[i]);
            else res.add(""+num);
        }
        return res;
    }

}
