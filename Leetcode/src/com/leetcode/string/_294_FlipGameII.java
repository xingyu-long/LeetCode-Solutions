package com.leetcode.string;

import java.util.HashMap;

public class _294_FlipGameII {
    /**
     *  294. Flip Game II
     *  When:2019/8/5
     *  Difficulty: Medium
     * @param s
     * @return
     */
    // recursion with memo time:不确定 space:O(n)
    public boolean canWin(String s) {
        if (s == null || s.length() == 0) return false;
        HashMap<String, Boolean> map = new HashMap<>();
        return helper(s, map);
    }

    public boolean helper(String s, HashMap<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!helper(opponent, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }

        map.put(s, false);
        return false;
    }
}