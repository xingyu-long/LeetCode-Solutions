package com.leetcode.string.Palindrome;

/**
 * @Date: 10/14/2020
 * @Description: greedy
 **/
public class _1616_Split_Two_Strings_to_Make_Palindrome {
    public boolean checkPalindromeFormation(String a, String b) {
        // 找最长的前后缀匹配。
        // 相当于可以看a，b两个字符串prefix 以及suffix匹配最长的palindrome
        return check(a, b) || check(b, a);
    }


    public boolean check(String a, String b) {
        int i = 0, j = b.length() - 1;
        while (i < a.length() && j >= 0 &&
                a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        return isValid(a, i, j) || isValid(b, i, j);
    }

    public boolean isValid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
