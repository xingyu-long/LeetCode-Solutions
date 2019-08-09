package com.leetcode.bitManipulation;

public class _389_FindTheDifference {
    /**
     * 389. Find The Difference
     * When: 2019/06/15
     * review1:2019/8/9
     * @param s
     * @param t
     * @return
     */
    // 暴力解法。 time:O(n^2) space:O(n)
    public char findTheDifference(String s, String t) {
        // brute force solution n*n
        StringBuilder ss = new StringBuilder(s);
        StringBuilder st = new StringBuilder(t);
        for (int i = 0; i < ss.length(); i++) {
            for (int j = 0; j < st.length(); j++) {
                if (ss.charAt(i) == st.charAt(j) && ss.charAt(i) != '-' && st.charAt(j) != '-') {
                    ss.setCharAt(i, '-');
                    st.setCharAt(j, '-');
                }
            }
        }
        for (int i = 0; i < st.toString().length(); i++) {
            if (st.charAt(i) != '-') {
                return st.charAt(i);
            }
        }
        return '0';
    }

    // 使用两个string的和之差就知道是哪个char
    // time: O(n) space: O(n)
    public char findTheDifference2(String s, String t) {
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int sums = 0;
        int sumt = 0;
        for (char c : chs) {
            sums += (int)c;
        }
        for (char c : cht) {
            sumt += (int)c;
        }
        return (char)(sumt - sums);
    }

    //利用位运算。其中异或 （相同为0，不同为1）
    public char findTheDifference3(String s, String t) {
        char res = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return res;
    }


    public static void main(String[] args) {
        char c = 'd';
        c ^= 'a';
        c ^= 'a';
        System.out.println(c);
    }
}
