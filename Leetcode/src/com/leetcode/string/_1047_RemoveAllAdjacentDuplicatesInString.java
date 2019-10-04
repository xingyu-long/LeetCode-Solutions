package com.leetcode.string;

import java.util.Stack;

public class _1047_RemoveAllAdjacentDuplicatesInString {

    /**
     * 1047. Remove All Adjacent Duplicates In String
     * When:2019/10/1
     * Difficulty: Easy
     * @param S
     * @return
     */
    public static String removeDuplicates(String S) {
        int i = 0, n = S.length();// i 表示待插入的位置，我们每次比较的是已经确定插入的位置，即i-1
        char[] res = S.toCharArray();
        for (int j = 0; j < n; ++j) {
            if (i > 0 && res[i - 1] == res[j]) {
                --i;
            } else {
                res[i] = res[j];
                i++;
            }
        }
        return new String(res, 0, i); // 这里的（0,i）可以取出来也是因为最后i有++的操作
    }

    // using stack
    // time:O(n) space:O(n)
    public String removeDuplicates2(String S) {
        // how to solve ?  Stack
        if (S.length() == 0 || S == null) return null;
        char[] ch = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : ch) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbbac"));
    }
}
