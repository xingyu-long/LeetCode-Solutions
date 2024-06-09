package com.ctci.ArrayAndString;

public class _1_3_URLify {

    /**
     * 2019/8/18
     *
     * URLify: Write a method to replace all spaces in a string with '%20'.
     * You may assume that the string has sufficient space at the end to hold the additional characters,
     * and that you are given the "true" length of the string. (Note: If implementing in Java,
     * please use a character array so that you can perform this operation in place.)
     *
     * solution:
     * 1. 使用split函数
     *
     *
     * hint
     * # 53 It's often easiest to modify strings by going from the end of the string to the beginning.
     * # 118 You might find you need to know the number of spaces. Can you just count them?
     * @param s
     * @return
     */
    public static String urlify(String s) {
        String[] arrayS = s.split("\\s+");
        String res = "";
        for (int i = 0; i < arrayS.length - 1; i++) {
            res += arrayS[i];
            res += "%20";
        }
        res += arrayS[arrayS.length - 1];
        return res;
    }


    public static void main(String[] args) {
        String s = "Mr John Smith ";
        System.out.println(urlify(s));
    }
}
