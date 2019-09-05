package com.leetcode.stackPriorityQueue;

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
    //time:O(n) space:O(n)
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
}
