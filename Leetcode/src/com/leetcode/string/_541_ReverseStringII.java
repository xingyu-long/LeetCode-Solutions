package com.leetcode.string;

public class _541_ReverseStringII {
    public String reverseStr(String s, int k) {
        // 转为char arr
        if (s == null || s.length() == 0) return "";
        if (k == 0) return s;
        char[] chs = s.toCharArray();
        int n = s.length();
        int i = 0;
        while (i < n) {
            int j = Math.min(i + k - 1, n - 1); // 相当于每次判断走了k的位置，与末尾比较
            exch(chs, i, j);
            i += 2 * k;
        }
        return new String(chs);
    }

    public void exch(char[] chs, int left, int right) {
        while (left < right) {
            char temp = chs[left];
            chs[left] = chs[right];
            chs[right] = temp;
            left++;
            right--;
        }
    }
}
