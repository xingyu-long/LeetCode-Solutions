package com.leetcode.string;

import java.util.Stack;

public class _1209_RemoveAllAdjacentDuplicatesinStringII {
    /**
     * 1209. Remove All Adjacent Duplicates in String II
     * When:2019/10/2
     * Difficulty: Medium
     * @param s
     * @param k
     * @return
     */
    // 需要和1047对比着看
    // 利用例子 abbac k=2 方便理解
    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() == 0) return null;
        char[] ch = s.toCharArray();
        int n = s.length();
        int[] count = new int[n];
        int i = 0; // 依然为待插入的位置
        for (int j = 0; j < n; j++) {
            ch[i] = ch[j];
            count[i] = i > 0 && ch[i - 1] == ch[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) {
                i -= k;
            }
            i++;
        }
        return new String(ch, 0, i);
    }

    // 利用栈，并且使用一个计数（负责记录当前charStack对应的个数）
    public String removeDuplicates2(String s, int k) {
        if (s == null || s.length() == 0) return null;
        // 利用stack
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!charStack.isEmpty() && charStack.peek() == c) {
                countStack.push(countStack.pop() + 1);
            } else {
                countStack.push(1);
            }
            charStack.push(c);
            if (countStack.peek() == k) {
                countStack.pop();
                for (int i = 0; i < k; i++) {
                    // countStack.pop();
                    charStack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!charStack.isEmpty()) {
            sb.append(charStack.pop());
        }
        return sb.reverse().toString();
    }
}
