package com.ctci.ArrayAndString;

public class _1_6_StringCompression {


    /**
     * 2019/8/18
     *
     * String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
     * For example, the string aabcccccaaa would become a2blc5a3.
     * If the "compressed" string would not become smaller than the original string,
     * your method should return the original string. You can assume the string
     * has only uppercase and lowercase letters (a - z).
     *
     * solution:
     * 依次计算每个的个数
     * @param s
     * @return
     */
    public static String compress(String s) {
        if (s.length() < 2) return "1" + s.charAt(0);
        String res = "";
        char index = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == index) {
                count++;
            } else {
                res += count;
                res += index;
                index = s.charAt(i);
                count = 1;
            }
        }
        res += count;
        res += index;
        return res;
    }

    // 这中写法更加优雅，需要记忆！
    // time:O(n) space:O(n)
    public static String compress2(String s) {
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                res.append(count);
                res.append(s.charAt(i));
                count = 0;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(compress2("AABCCD"));
    }
}
