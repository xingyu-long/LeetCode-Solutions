package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _316_RemoveDuplicateLetters {
    //time:O(n) space:O(n)
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        int[] counter = new int[128];
        for (char ch : s.toCharArray()) counter[ch]++;
        boolean[] visited = new boolean[128];
        StringBuilder res = new StringBuilder("0");
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            counter[ch]--;
            if (visited[ch]) continue;
            while (ch < res.charAt(res.length() - 1) && counter[res.charAt(res.length() - 1)] > 0) {
                visited[res.charAt(res.length() - 1)] = false;
                res.deleteCharAt(res.length() - 1);
            }
            res.append(ch);
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
