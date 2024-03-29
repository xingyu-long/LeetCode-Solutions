package com.leetcode.stack_priority_queue;

import java.util.HashMap;
import java.util.Map;

public class _388_LongestAbsoluteFilePath {

    // time: O(n) space: O(map)
    public int lengthLongestPath(String input) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (String s : input.split("\n")) {
            // if not exist, it will return -1
            int level = s.lastIndexOf("\t") + 1;
            int len = s.substring(level).length();
            if (s.contains(".")) {
                res = Math.max(res, map.get(level) + len);
            } else {
                // 把当前层的dir的长度给到下一层
                // +1是代表 '/'
                map.put(level + 1, map.get(level) + len + 1);
            }
        }
        return res;
    }
}