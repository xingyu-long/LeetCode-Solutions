package com.leetcode.string.Palindrome;

/**
 * @Date: 01/25/2020, 05/08/2020
 * @Description: Palindrome
 **/
public class _1328_BreakaPalindrome {

    // time:O(n) space: O(n)
    public String breakPalindrome(String palindrome) {
        // 全是a的情况
        // 走到尽头但是中途改变不会使其无效 aabaa -> 所以只能改最后一个字符才对
        // edge case 比较多
        // edge case:
        // "aa" -> "ab";
        // "aaabaaa" -> "aaabaab"
        // change the first char which is not a.
        if (palindrome == null || palindrome.length() == 0) {
            return "";
        }
        char[] chs = palindrome.toCharArray();
        boolean atEnd = false;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] != 'a') {
                char ch = chs[i];
                chs[i] = 'a';
                if (!isValid(new String(chs))) {
                    return new String(chs);
                }
                chs[i] = ch;
            }
            // 改变后不符合回文或者是全部是'a'
            if (i == chs.length - 1) {
                atEnd = true;
            }
        }
        if (atEnd && chs.length > 1) {
            chs[chs.length - 1] = 'b';
            return new String(chs);
        }
        return "";
    }

    public boolean isValid(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 可以用走完全部，因为Palindrome的关系，我们只需要验证其中一半就好
    // https://leetcode.com/problems/break-a-palindrome/discuss/489774/JavaC%2B%2BPython-Easy-and-Concise
    // time:O(n) space:O(n)
    public String breakPalindrome2(String palindrome) {
        if (palindrome == null || palindrome.length() == 0) {
            return "";
        }
        char[] chs = palindrome.toCharArray();
        int n = palindrome.length();
        for (int i = 0; i < n / 2; i++) {
            if (chs[i] != 'a') {
                chs[i] = 'a';
                return new String(chs);
            }
        }
        // if all 'a'
        chs[n - 1] = 'b';
        // 'a' -> ""
        return n < 2 ? "" : new String(chs);
    }
}
