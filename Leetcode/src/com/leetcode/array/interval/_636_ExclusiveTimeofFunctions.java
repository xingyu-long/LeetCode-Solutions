package com.leetcode.array.interval;

import java.util.List;
import java.util.Stack;

/**
 * @Date: 05/17/2020
 * @Description: Interval, Stack
 **/
public class _636_ExclusiveTimeofFunctions {
    // time:O(len(logs)) space:O(n + len(logs))
    public int[] exclusiveTime(int n, List<String> logs) {
        // 用prev来interval的开始
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prev = 0;// the start of the interval
        for (String log : logs) {
            String[] str = log.split(":");
            if (str[1].equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += Integer.parseInt(str[2]) - prev;
                }
                stack.push(Integer.parseInt(str[0]));
                prev = Integer.parseInt(str[2]);
            } else { // end
                res[stack.pop()] += Integer.parseInt(str[2]) - prev + 1;
                prev = Integer.parseInt(str[2]) + 1;
            }
        }
        return res;
    }
}
