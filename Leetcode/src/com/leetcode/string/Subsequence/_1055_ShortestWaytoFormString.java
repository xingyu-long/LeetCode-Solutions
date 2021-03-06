package com.leetcode.string.Subsequence;

public class _1055_ShortestWaytoFormString {
    // Input: source = "xyz", target = "xzyxz"
    // Output: 3
    // Explanation: The target string can be constructed as follows "xz" + "y" + "xz".

    // 可以利用isSubsequence里面的方法进行暴力解。
    // time:O(len(target) * x * len(source)) x = 几个不同的分割字符串组成target
    //-> time:O(len(target) * x * log(len(source)))
    public static int shortestWay(String source, String target) {
        boolean[] isExist = new boolean[26];
        for (char ch : source.toCharArray()) {
            isExist[ch - 'a'] = true;
        }

        // i, source
        // j, target
        int i = 0, j = 0;
        int res = 0;
        while (j < target.length()) {
            if (!isExist[target.charAt(j) - 'a']) return -1;
            if (i < source.length() && source.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
            if (i == source.length()) {
                res++;
                i = 0;// 从头开始搜索
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(shortestWay("abc", "abcbc"));
    }
}
