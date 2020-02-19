package com.leetcode.string.TwoPointer;

public class _859_BuddyStrings {
    // n^2  TLE
    // 如果两个字符串相同，也需要看是否有两个以上的相同字符的，如果有说明可以交换
    // 如果两个字符串不相同，就可以就去找两个不相同的位置，如果有多的不相同就return false.
    // 用的挺妙的。
    // two pointer?  相同的时候一开始没有想到用counting来做
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            // 字母组成里面有字符大于1的话就可以 "aa" 因为这个本身可以交换
            int[] count = new int[26];
            for (char ch : A.toCharArray()) count[ch - 'a']++;
            for (int c : count) if (c > 1) return true;
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }
            return second != -1 &&
                    A.charAt(first) == B.charAt(second) &&
                    A.charAt(second) == B.charAt(first);
        }
    }
}
