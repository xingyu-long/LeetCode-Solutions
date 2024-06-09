package com.intern.Amazon;

public class ConverseAtoNum {
    public static void  converse(String s) {
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            System.out.print(ch - 'A' + 1 + " ");
        }
    }

    public static void main(String[] args) {
        String[] strs = {"STICK", "RSHBJ", "REPLY"};
        for (String s : strs) {
            converse(s);
            System.out.println();
        }
    }
}
