package com.leetcode.dynamic_programming.back_check_dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 04/28/2020
 * @Description: 往回看DP
 **/
public class _1048_LongestStringChain {

    // Time: O(len(words) * len(word) * len(word))
    // 优化了往前看的情况，内部是有顺序的！！！例如ab 不能变为bac
    public int longestStrChain2(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        Map<String, Integer> map = new HashMap<>();
        int n = words.length, res = 0;
        // 优化了找寻的过程
        for (String word : words) {
            int max = 0;
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                max = Math.max(max, map.getOrDefault(prev, 0));
            }
            max += 1; // 加上当前为结尾的情况
            map.put(word, max);
            res = Math.max(res, max);
        }
        return res;
    }
}
