package com.leetcode.string;

public class _38_CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) return "1";
        String res = "1";
        int index = 1;
        while (index < n) {
            StringBuilder sb = new StringBuilder();
            int j = 0;

            while (j < res.length()) {
                char ch = res.charAt(j);
                int count = 1;
                // 一直寻找一样的情况！
                while (j + 1 < res.length() && res.charAt(j + 1) == res.charAt(j)) {
                    count++;
                    j++;
                }
                sb.append(count);
                sb.append(ch);
                j++;
            }
            res = sb.toString();
            index++;
        }
        return res;
    }
}
