package com.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 05/06/2020, 10/24/2020
 * @Description: Greedy,
 **/
public class _659_SplitArrayintoConsecutiveSubsequences {
    // https://www.youtube.com/watch?v=uJ8BAQ8lASE
    // 分类讨论：继续着上次的长度然后加1个数；重新开一个至少为3长度的序列
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();
        // 统计
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            }
            if (need.getOrDefault(num, 0) > 0) {
                // 连接到以前的后面（之前肯定有三个即以上的个数了）
                need.put(num, need.get(num) - 1);
                need.put(num + 1, need.getOrDefault(num + 1, 0) + 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                // 组成新的三个
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
            freq.put(num, freq.get(num) - 1); // 这一步没有想到
        }
        return true;
    }
}
