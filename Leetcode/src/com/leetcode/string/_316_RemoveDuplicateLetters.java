package com.leetcode.string;

public class _316_RemoveDuplicateLetters {

    /**
     *  316. Remove Duplicate Letters
     *  Time: 2019/7/17
     *  Difficulty: Hard
     *
     *  参考网址：
     *  https://www.cnblogs.com/grandyang/p/5085379.html
     *
     *  solution:
     *  主要是利用visited数组来记录出现情况，然后当前遍历的字符小于目前结果的最后一位，那么将该位弹出来并且visited置为0（因为后面也会出现）
     *
     * @param s
     * @return
     */
    //time:O(n) space:O(n)
    public static String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        int[] visited = new int[26];
        String res = "0";
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            --count[index];
            if (visited[index] == 1) continue;
            while (s.charAt(i) < res.charAt(res.length() - 1) &&
                    count[res.charAt(res.length() - 1) - 'a'] > 0) {
                visited[res.charAt(res.length() - 1) - 'a'] = 0;
                res = res.substring(0, res.length() - 1);
                // 这里要注意 substring(startIndex, length); !!!!
            }
            res += s.charAt(i);
            visited[s.charAt(i) - 'a'] = 1;
        }
        return res.substring(1);
    }

    public static void main(String[] args) {
        String s = "01234";
        System.out.println(s.substring(0, 4));
    }
}
