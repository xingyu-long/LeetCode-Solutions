package com.leetcode;

public class ReverseWordsInAString {



    /**
     *
     * 151. Reverse Words in a String
     * when: 2019/03/17
     *
     * 思路：
     * solution1: 直接使用split('\\s+')进行分割 反转，然后中途每个单词+“ ”即可
     * solution2:
     * 涉及到的数据结构或者方法：
     * StringBuilder 以及添加的append方法
     *  视频里面提到了使用toArrayChar()
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        if (s==null || s.length() == 0) return s;
        String[] str =  s.split("\\s+");
        reverse(str, 0, str.length - 1);

        String res = "";
        for (String ss: str){
            res = res + ss + " ";
            System.out.println(ss);
        }
        return res.trim();


        /**
         *
         * 利用stringBuilder
         *         StringBuilder sb = new StringBuilder();
         *         String[] words = s.trim().split("\\s+");
         *         for (int i = words.length - 1; i >= 0; i--){
         *             sb.append(words[i]+" ");
         *         }
         *         return sb.toString().trim();
         */

    }

    public static void reverse(String[] Str, int start, int end){
        while (start < end){
            String temp = Str[start];
            Str[start] = Str[end];
            Str[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args){
        System.out.println(reverseWords("hello world! xx"));
    }
}
