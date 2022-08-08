package com.leetcode.string;

/**
 * @Date: 07/15/2020
 * @Description: String, reverse
 **/
public class _541_ReverseStringII {

    public String reverseStr(String s, int k) {
        char[] chs = s.toCharArray();
        int left = s.length();
        int n = left, index = 0;
        while (index < n) {
            if (left >= 2 * k) {
                reverse(chs, index, index + k);
                index += 2 * k;
                left -= 2 * k;
            } else if (left >= k) {
                reverse(chs, index, index + k);
                index += 2 * k;
                left -= 2 * k;
            } else if (left < k) {
                reverse(chs, index, index + left);
                index += left;
                left = 0;
            }
        }
        return new String(chs);
    }

    private void reverse(char[] chs, int left, int right) {
        right -= 1;
        while (left < right) {
            char temp = chs[left];
            chs[left] = chs[right];
            chs[right] = temp;
            left++;
            right--;
        }
    }
}
