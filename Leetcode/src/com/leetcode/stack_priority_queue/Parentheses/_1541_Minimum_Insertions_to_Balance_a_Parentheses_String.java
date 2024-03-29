package com.leetcode.stack_priority_queue.Parentheses;

public class _1541_Minimum_Insertions_to_Balance_a_Parentheses_String {
    // 遇到左括号count++
    // 然后去找连续的两个右括号是否存在
    public int minInsertions(String s) {
        int n = s.length();
        int count = 0;
        int res = 0;
        int index = 0;
        while (index < n) {
            if (s.charAt(index) == '(') {
                count++;
                index++;
            } else if (s.charAt(index) == ')') {
                if (index + 1 < n && s.charAt(index + 1) == ')') {
                    if (count > 0) count--;
                    else res += 1;
                    index += 2;
                } else { // 走完或者是第二个char不是')'
                    if (count > 0) count--; // 这个还是要消除前面的'('
                    else res++; // 不然就是需要加上'('
                    res += 1; // 这个表示少的那个')'
                    index += 1; // 继续走一步。
                }
            }
        }
        return res + 2 * count;
    }
}
