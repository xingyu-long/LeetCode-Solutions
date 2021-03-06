package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Date: 05/17/2020
 * @Description: Backtracking, Bit Mask
 **/
public class _1239_MaximumLengthofaConcatenatedStringwithUniqueCharacters {
    private int res;
    // time:O(2^n) space:O(2^n * isUnique)
    public int maxLength(List<String> arr) {
        res = 0;
        dfs(arr, "", 0);
        return res;
    }
    // [123] -> "", 1, 2, 3, 12, 13, 23, 123 有按照顺序的 需要加入index 辅助
    private void dfs(List<String> arr, String curr, int index) {
        boolean unique = isUnqiue(curr);
        if (unique) {
            res = Math.max(res, curr.length());
        }
        if (!unique || index == arr.size()) {
            return;
        }
        for (int i = index; i < arr.size(); i++) {
            dfs(arr, curr + arr.get(i), i + 1);
        }
    }

    private boolean isUnqiue(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (!set.add(ch)) return false;
        }
        return true;
    }

    // 利用bit manipulation
    public int maxLength2(List<String> arr) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int res = 0;
        for (String s : arr) {
            int a = 0, dup = 0;
            for (char ch : s.toCharArray()) {
                dup |= a & (1 << (ch - 'a')); // 看是否有重复的char
                a |= 1 << (ch - 'a'); // 记录当前的s组成的二进制表达的情况
            }

            if (dup > 0) {
                continue;
            }

            for (int i = dp.size() - 1; i >= 0; i--) {
                if ((dp.get(i) & a) > 0) {
                    continue;
                }
                // 说明可以组成符合情况的字符串
                dp.add(dp.get(i) | a);
                res = Math.max(res, Integer.bitCount(dp.get(i) | a));
            }
        }
        return res;
    }
}
