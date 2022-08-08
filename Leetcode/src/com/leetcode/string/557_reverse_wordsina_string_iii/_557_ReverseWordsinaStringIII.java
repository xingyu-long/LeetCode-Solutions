package com.leetcode.string;

/**
 * @Date: 07/15/2020
 * @Description: String, reverse
 **/
public class _557_ReverseWordsinaStringIII {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] strs = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            char[] chs = strs[i].toCharArray();
            reverse(chs, 0, chs.length - 1);
            sb.append(new String(chs));
            if (i != strs.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private void reverse(char[] ch, int i,  int j) {
        while (i < j) {
            char temp = ch[i];
            ch[i++] = ch[j];
            ch[j--] = temp;
        }
    }
}
