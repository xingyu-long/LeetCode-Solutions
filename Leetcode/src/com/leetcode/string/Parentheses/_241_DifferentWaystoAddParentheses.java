package com.leetcode.string.Parentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * _241_DifferentWaystoAddParentheses
 */
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
}