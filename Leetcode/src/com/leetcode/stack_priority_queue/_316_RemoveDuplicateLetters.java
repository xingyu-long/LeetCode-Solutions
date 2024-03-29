package com.leetcode.stack_priority_queue;

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

    public String removeDuplicateLetters2(String s) {
        // mono stack
        int[] count = new int[128];
        for (char ch : s.toCharArray()) {
            count[ch]++;
        }
        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            count[ch]--;
            if (set.contains(ch)) continue;
            while (!stack.isEmpty() && ch <= stack.peek() && count[stack.peek()] > 0) {
                // we can use this later
                set.remove(stack.peek());
                stack.pop();
            }
            stack.push(ch);
            set.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        // from bottom to top
        for (char ch : stack) {
            sb.append(ch);
        }
        // System.out.println(sb.toxString());
        return sb.toString();
    }
}
