package com.intern.Quora;

import java.util.HashSet;

public class divideSubString {

    public static int divideSubString(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n - k + 1; i++) {
            int a = Integer.parseInt(s);
            int b = Integer.parseInt(s.substring(i, i + k));
            if (a % b == 0) set.add(b);
        }
        return set.size();
    }


    public static void main(String[] args) {
        System.out.println(divideSubString("5555", 1));
    }
}
