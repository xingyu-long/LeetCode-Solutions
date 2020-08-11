package com.leetcode.stackPriorityQueue.Parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 04/25/2020
 * @Description: DFS, Parentheses
 **/
public class _301_RemoveInvalidParentheses {

    // time:O(2^{L + R}) space:O((l + r)^2 -> n2)
    public List<String> removeInvalidParentheses(String s) {
        //1. 删除左边和右边分别需要多少
        //2. 依次删除并且验证是否为valid
        int L = 0;
        int R = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                L += 1;
            }

            if (L == 0) {
                if (ch == ')') {
                    R += 1;
                }
            } else {
                if (ch == ')') {
                    L -= 1;
                }
            }
        }
        List<String> res = new ArrayList<>();
        dfs(s, 0, L, R, res);
        return res;
    }

    public void dfs(String s, int start, int L, int R, List<String> res) {
        if (L == 0 && R == 0) {
            // System.out.println("before we add to the result, current String s = " + s);
            if (isValid(s)) {
                // System.out.println("1");
                res.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            char ch = s.charAt(i);
            if (ch == '(' || ch == ')') {
                String curr = s.substring(0, i) + s.substring(i + 1); // 这里应该是构建字符串的样子
                if (R > 0 && ch == ')') {
                    dfs(curr, i, L, R - 1, res);
                }
                if (L > 0 && ch == '(') {
                    dfs(curr, i, L - 1, R, res);
                }
            }
        }
    }


    public boolean isValid(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            }
            if (ch == ')') {
                count--;
            }
            if (count < 0) {
                return false; // 说明右括号比左左括号多。
            }
        }
        return count == 0;
    }
}
