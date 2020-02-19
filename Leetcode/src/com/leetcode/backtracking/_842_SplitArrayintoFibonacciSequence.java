package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _842_SplitArrayintoFibonacciSequence {
    // 和306一个做法
    // time:O(10 * 10 * n) 因为题目有限制
    public List<Integer> splitIntoFibonacci(String S) {
        // addictive number 一样的思路 题目的数据规模所以最长应该是10。
        int n = S.length();
        List<Integer> res = new ArrayList<>();
        int maxLen = 10;
        for (int i = 1; i <= Math.min(10, n); i++) {
            if (S.charAt(0) == '0' && i > 1) return res;// it is empty;
            Long first = Long.valueOf(S.substring(0, i));
            for (int j = 1; n - i - j >= Math.min(i, j) && j <= 10; j++) {
                if (S.charAt(i) == '0' && j > 1) break;
                Long second = Long.valueOf(S.substring(i, i + j));
                res.add(first.intValue());
                res.add(second.intValue());
                if (dfs(S, first, second, i + j, res)) return res;
                res.remove(res.size() - 1);
                res.remove(res.size() - 1);
            }
        }
        return res;
    }

    public boolean dfs(String s, Long first, Long second, int index, List<Integer> res) {
        if (index == s.length() && res.size() >= 3) return true;
        second = first + second;
        first = second - first;
        if (second > Integer.MAX_VALUE) return false;
        String sum = String.valueOf(second);
        if (!s.startsWith(sum, index)) return false;
        res.add(second.intValue());
        if (dfs(s, first, second, index + sum.length(), res)) return true;
        res.remove(res.size() - 1);
        return false;
    }
}
