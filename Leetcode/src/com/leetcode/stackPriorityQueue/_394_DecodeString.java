/*
 * @Date: 07/05/2022 17:34:48
 * @LastEditTime: 07/30/2022 11:01:17
 * @Description: You need to fill out
 */
package com.leetcode.stackPriorityQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _394_DecodeString {
    // time: O(n) space:O(n)
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int index = 0, n = s.length();
        char[] chs = s.toCharArray();
        int count = 0;
        String curr = "";
        Stack<String> strStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        while (index < n) {
            if (Character.isDigit(chs[index])) {
                while (index < n && Character.isDigit(chs[index])) {
                    count = count * 10 + (chs[index] - '0');
                    index++;
                }
                countStack.push(count);
                count = 0;
            } else if (chs[index] == '[') {
                // 对于3[a]这种情况，则会将 "" 加入strStack里面
                strStack.push(curr);
                curr = "";
                index++;
            } else if (chs[index] == ']') {
                // pop with previous str
                StringBuilder temp = new StringBuilder(strStack.pop());
                // concat curr string with count
                int times = countStack.pop();
                for (int i = 0; i < times; i++) {
                    temp.append(curr);
                }
                curr = temp.toString();
                index++;
            } else {
                curr += chs[index];
                index++;
            }
        }
        return curr;
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