package com.leetcode.string;

import java.util.Stack;

public class _316_RemoveDuplicateLetters {

    /**
     *  316. Remove Duplicate Letters
     *  Time: 2019/7/17
     *  Difficulty: Hard
     *
     *  参考网址：
     *  https://www.cnblogs.com/grandyang/p/5085379.html
     *
     *  solution:
     *  主要是利用visited数组来记录出现情况，然后当前遍历的字符小于目前结果的最后一位，那么将该位弹出来并且visited置为0（因为后面也会出现）
     *
     * @param s
     * @return
     */
    //time:O(n) space:O(n)
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        int[] counter = new int[128];
        for (char ch : s.toCharArray()) counter[ch]++;
        boolean[] visited = new boolean[128];
        String res = "0";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            counter[ch]--;
            if (visited[ch]) continue;
            while (ch < res.charAt(res.length() - 1) && counter[res.charAt(res.length() - 1)] > 0) {
                visited[res.charAt(res.length() - 1)] = false;
                res = res.substring(0, res.length() - 1);
            }
            res += ch;
            visited[ch] = true;
        }
        return res.substring(1);
    }

    // 利用stack，一样的思路，但这样不会用substring。
    public String removeDuplicateLetters2(String s) {
        if (s == null || s.length() == 0) return "";
        int[] count = new int[128];
        for (char ch : s.toCharArray()) count[ch]++;
        boolean[] visited = new boolean[128];
        Stack<Character> stack = new Stack<>();
        stack.push('0');
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count[ch]--;
            if (visited[ch]) continue;
            while (ch < stack.peek()
                    && count[stack.peek()] > 0) {
                visited[stack.peek()] = false;
                stack.pop();
            }
            stack.push(ch);
            visited[ch] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.reverse().toString();
        // "cbacdcbc"
    }

    public static void main(String[] args) {
        String s = "01234";
        System.out.println(s.substring(0, 4));
    }
}
