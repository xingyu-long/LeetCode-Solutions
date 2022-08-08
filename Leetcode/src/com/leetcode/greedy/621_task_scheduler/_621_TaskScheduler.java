package com.leetcode.greedy;

import java.util.Arrays;

/**
 * @Date: 05/14/2020
 * @Description: Greedy,
 **/
public class _621_TaskScheduler {
    // time:O(nlogn)
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }
        Arrays.sort(count);
        int i = 25, max = count[25], len = tasks.length;
        while (i >= 0 && count[i] == max) {
            i--;
        }
        // 有max - 1部分，每个部分有n + 1个字符（不够的由idle凑满） 25-i表示最后需要加的一部分
        return Math.max(len, (max - 1) * (n + 1) + (25 - i));
    }
}
