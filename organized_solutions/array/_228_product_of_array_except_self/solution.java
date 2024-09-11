/*
 * @Date: 08/11/2020 16:07:14
 * @LastEditTime: 06/05/2022 11:21:09
 * @Description: Simulation
 */
package com.leetcode.array.counter;

import java.util.ArrayList;
import java.util.List;

public class _228_SummaryRanges {
    // time: O(n), space: O(1) except the result array.
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int index = 0;
        while (index < n) {
            int start = nums[index];
            while (index + 1 < n && nums[index + 1] == nums[index] + 1) {
                index++;
            }
            int end = nums[index];
            if (start != end) res.add(start + "->" + end);
            else res.add("" + start);
            index++;
        }
        return res;
    }
}
