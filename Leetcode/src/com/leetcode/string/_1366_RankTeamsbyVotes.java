package com.leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class _1366_RankTeamsbyVotes {

    /**
     * When: 03/01/2020, 3/9/2020
     * @param votes
     * @return
     */
    // time:O(len log (len)) space:O(n)
    public String rankTeams(String[] votes) {
        HashMap<Character, int[]> map = new HashMap<>();
        int len = votes[0].length();
        // 标记每个字符在每个位置出现的次数。
        for (String vote : votes) {
            for (int i = 0; i < len; i++) {
                char ch = vote.charAt(i);
                map.putIfAbsent(ch, new int[len]);
                map.get(ch)[i]++;
            }
        }
        
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b) -> {
            for (int i = 0; i < len; i++) {
                if (map.get(a)[i] != map.get(b)[i]) {
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            // 表示出现全都相同
            return a - b;
        });
        
        StringBuilder sb = new StringBuilder();
        for (char ch : list) sb.append(ch);
        return sb.toString();
    }
}