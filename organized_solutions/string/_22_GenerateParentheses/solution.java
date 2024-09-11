package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _22_GenerateParentheses {

    /**
     * 22. Generate Parentheses
     * When:2019/7/15
     * review1:11/6/2019
     * solution:
     * 使用backtracking，主要是看左右两边均为0的情况则属于合法
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        helper(res, "", n, n);
        return res;
    }

    public static void helper(List<String> res, String s, int left, int right) {
        // left, right均表示可以放置在那个位置的个数
        // 防止以右边 ) 开头
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) {
            helper(res, s + "(", left - 1, right);
        }
        if (right > 0) {
            helper(res, s + ")", left, right - 1);
        }
    }

    // 这样更好理解一些
    public List<String> generateParenthesis2(int n) {
        if (n <= 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        helper(res, "", 0, 0, n);
        return res;
    }

    public void helper(List<String> res, String s, int left, int right, int n) {
        // base case
        if (left > n || right > n) return;
        // goal;
        if (left == n && right == n) {
            res.add(s);
        }

        helper(res,  s + "(", left + 1, right, n);
        if (right < left) { // constraints.
            helper(res,  s + ")", left, right + 1, n);
        }
    }

}
