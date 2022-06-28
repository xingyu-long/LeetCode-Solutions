package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _1047_RemoveAllAdjacentDuplicatesInString {

    /**
     * 1047. Remove All Adjacent Duplicates In String
     * When:2019/10/1
     * Difficulty: Easy
     * @param S
     * @return
     */
    // 这个相当于是模拟栈的操作，使用i。
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

    // time:O(n^2) space:O(1) 比较慢 是因为有substring的操作
    // substring 会耗掉O(n)
    public String removeDuplicates3(String S) {
        int index = 0;
        while (index + 1 < S.length()) {
            if (S.charAt(index) == S.charAt(index + 1)) {
                // 这个是跳跃多个的时候。
                // int j = index + 1;
                // while (j < S.length() && S.charAt(j) == S.charAt(index + 1)) {
                //     j++;
                // }
                S = S.substring(0, index) + S.substring(index + 2);
                index = 0;
            } else {
                index++;
            }
        }
        return S;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("abbbac"));
    }
}
