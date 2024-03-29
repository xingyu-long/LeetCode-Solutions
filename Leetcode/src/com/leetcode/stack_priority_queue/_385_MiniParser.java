package com.leetcode.stack_priority_queue;

import com.leetcode.common.NestedInteger;

import java.util.Stack;

public class _385_MiniParser {
    /**
     * 385. MiniParser
     * When:2019/8/25
     * review1:2019/9/4
     * Difficulty: medium
     *
     * @param s
     * @return
     */
    // 利用stack
    //time:O(n) space:O(n)
    // 相当于利用 ']' 来让其可以包含起来
    public NestedInteger deserialize(String s) {
        if (!s.startsWith("[")) {
            return new NestedInteger(Integer.valueOf(s));
        }
        Stack<NestedInteger> stack = new Stack<>();
        int start = 1; // 利用i和start确定数字的位置
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(new NestedInteger());
                start = i + 1;
            } else if (c == ',' || c == ']') {
                if (i > start) {
                    Integer val = Integer.valueOf(s.substring(start, i));
                    stack.peek().add(new NestedInteger(val));
                }
                start = i + 1;
                if (c == ']') {
                    if (stack.size() > 1) {
                        NestedInteger nestedInteger = stack.peek();
                        stack.pop();
                        stack.peek().add(nestedInteger);
                    }
                }
            }
        }
        return stack.peek();
    }

    // recursion
    // 只是这里花费了很多时间自己在做自己的，但发现中间过程有问题
    public NestedInteger deserialize2(String s) {
        if (s == null || s.length() == 0) return null;
        return dfs(s);
    }

    public NestedInteger dfs(String s) {
        if (s == null || s.length() == 0) return new NestedInteger();
        if (s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
        if (s.length() <= 2) return new NestedInteger();
        NestedInteger res = new NestedInteger();
        int left = 1, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (count == 0 && (s.charAt(i) == ',' || i == s.length() - 1)) {
                res.add(dfs(s.substring(left, i)));
                left = i + 1;
            } else if (s.charAt(i) == '[') count++;
            else if (s.charAt(i) == ']') count--;
        }
        return res;
    }
}
