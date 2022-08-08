package com.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class _833_FindAndReplaceinString {

    // time:O(n) space:O(n)
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        // 先记录在那个点可以成功替换。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            if (S.startsWith(sources[i], indexes[i])) {
                map.put(indexes[i], i);
            }
        }

        int index = 0; // 这里也可以解决就算输入非增序的情况
        while (index < S.length()) {
            if (map.containsKey(index)) {
                // 表示从这里可以替换
                sb.append(targets[map.get(index)]);
                index += sources[map.get(index)].length();
            } else {
                sb.append(S.charAt(index));
                index++;
            }
        }
        return sb.toString();
    }
}
