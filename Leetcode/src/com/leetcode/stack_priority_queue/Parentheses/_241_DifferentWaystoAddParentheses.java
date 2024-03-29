package com.leetcode.stack_priority_queue.Parentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 05/08/2020
 * @Description: Divide and Conquer
 **/
public class _241_DifferentWaystoAddParentheses {

    // 分治
    // 这里的记忆化例如的话 就是 4*5的时候是重复计算
    // 这里已经记忆化递归了，
    // O(Cat(n))，space: O(n*Cat(n))
    HashMap<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input)) map.get(input);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '-' || ch == '+' || ch == '*') {
                String a = input.substring(0, i);
                String b = input.substring(i + 1);
                List<Integer> al = diffWaysToCompute(a);
                List<Integer> bl = diffWaysToCompute(b);
                for (int x : al) {
                    for (int y : bl) {
                        if (ch == '+') {
                            res.add(x + y);
                        } else if (ch == '-') {
                            res.add(x - y);
                        } else if (ch == '*') {
                            res.add(x * y);
                        }
                    }
                }
            }
        }
        // 这个也需要注意放置的位置。
        // 仅仅为数字的情况 single number
        if (res.size() == 0) {
            // 这个相当于base case了
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);
        return res;
    }

    public List<Integer> diffWaysToCompute2(String input) {
        // 分治
        if (input == null || input.length() == 0) {
            return new ArrayList<>();
        }
        int n = input.length();
        return dfs(input, 0, n - 1);
    }

    public List<Integer> dfs(String s, int i, int j) {

        List<Integer> res = new ArrayList<>();
        if (i > j) {
            return res;
        }
        for (int k = i; k <= j; k++) {
            char ch = s.charAt(k);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> left = dfs(s, i, k - 1);
                List<Integer> right = dfs(s, k + 1, j);
                for (int n1 : left) {
                    for (int n2 : right) {
                        if (ch == '+') res.add(n1 + n2);
                        else if (ch == '-') res.add(n1 - n2);
                        else if (ch == '*') res.add(n1 * n2);
                    }
                }
            }
        }
        // base case?
        // 如果全是数字，则转换成int 并且加入结果中
        if (res.size() == 0) {
            res.add(Integer.parseInt(s.substring(i, j + 1)));
        }

        return res;
    }
}