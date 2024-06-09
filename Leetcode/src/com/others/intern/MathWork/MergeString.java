package com.intern.MathWork;

public class MergeString {

    // 类似于merge sort，比较简单。
    public static String merge(String s1, String s2) {
        if ((s1 == null || s1.length() == 0)
                && (s2 == null || s2.length() == 0)) return  "";
        if (s1 == null || s1.length() == 0) return s2;
        if (s2 == null || s2.length() == 0) return s1;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        boolean flag = true;
        while (i < s1.length() && j < s2.length()) {
            if (flag) {
                sb.append(s1.charAt(i++));
                flag = false;
            } else {
                sb.append(s2.charAt(j++));
                flag = true;
            }
        }
        while (i < s1.length()) {
            sb.append(s1.charAt(i++));
        }

        while (j < s2.length()) {
            sb.append(s2.charAt(j++));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(merge("abc3333", "def"));
    }
}
