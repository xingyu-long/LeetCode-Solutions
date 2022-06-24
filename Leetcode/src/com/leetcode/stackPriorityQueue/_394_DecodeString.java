package com.leetcode.stackPriorityQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _394_DecodeString {
    /**
     *  394. Decode String
     *  When:2019/7/12
     *  Difficulty: Medium
     *
     * @param s
     * @return
     */
    // time: O(n) space:O(n)
    public static String decodeString(String s) {
        if (s == null || s.length() == 0) return s;
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = count * 10 + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                // 这里获得之前的结果，即resStack。
                StringBuilder temp = new StringBuilder(resStack.pop());
                int time = countStack.pop();
                for (int i = 0; i < time; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    // DFS with Queue
    public String decodeString2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Queue<Character> queue = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            queue.offer(ch);
        }
        return decode(queue);
    }
    
    public String decode(Queue<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '[') {
                String rest = decode(queue);
                for (int i = 0; i < num; i++) {
                    sb.append(rest);
                }
                num = 0;
            } else if (ch == ']') {
                break;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}