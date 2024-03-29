package com.leetcode.stack_priority_queue.Parentheses;

import java.util.Stack;

/**
 * @Date: 05/07/2020
 * @Description: Parentheses, Stack
 **/
public class _1021_RemoveOutermostParentheses {
    public String removeOuterParentheses(String S) {
        // 相当于用stack遍历？ 然后就一旦满足一次之后就把字符串弄出来，并且删除开头和结尾的一个字符
        if (S == null || S.length() == 0) {
            return "";
        }
        int count = 0;// 利用这个代替stack的作用
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, n = S.length();
        char[] chs = S.toCharArray();
        while (j < n) {
            char ch = chs[j];
            if (ch == '(') {
                count++;
            } else if (ch == ')'){
                count--;
            }
            if (count == 0) {
                // System.out.println("i = " + i + " j = " + j);
                // j - i + 1 - 2
                sb.append(new String(chs, i + 1, j - i - 1)); // offset, count
                i = j + 1;
            }
            j++;
        }
        return sb.toString();
    }
}
