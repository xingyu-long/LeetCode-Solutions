package com.leetcode.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Date: 2019/03/29, 2019/8/7, 06/01/2020
 * @Description: Sort
 **/
public class _179_LargestNumber {

    // time:O(nlogn) space:O(n)
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        // 如何处理30, 3这种情况是关键！
        // sort
        Arrays.sort(strs, (s1, s2) -> {
            String a = s1 + s2;
            String b = s2 + s1;
            return b.compareTo(a);
        });
        if (strs[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }
}
