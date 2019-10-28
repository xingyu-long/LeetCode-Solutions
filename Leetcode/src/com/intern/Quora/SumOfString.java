package com.intern.Quora;

public class SumOfString {

    public static String sumOfString(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return s2;
        if (s2 == null || s2.length() == 0) return s1;
        StringBuilder sb = new StringBuilder();
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        while (i >= 0 && j >= 0) {
            long a = s1.charAt(i) - '0';
            long b = s2.charAt(j) - '0';
            sb.insert(0, Long.toString(a + b));
            i--;
            j--;
        }
        while (i >= 0) {
            sb.insert(0, s1.charAt(i));
            i--;
        }

        while (j >= 0) {
            sb.insert(0, s2.charAt(j));
            j--;
        }
        return sb.toString();
    }
}
