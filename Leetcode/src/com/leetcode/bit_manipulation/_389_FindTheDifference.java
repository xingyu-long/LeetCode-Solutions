package com.leetcode.bit_manipulation;

/**
 * @Date: 07/17/2020
 * @Description: xor
 **/
public class _389_FindTheDifference {

    // 使用两个string的和之差就知道是哪个char
    // time: O(n) space: O(n)
    public char findTheDifference2(String s, String t) {
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int sums = 0;
        int sumt = 0;
        for (char c : chs) {
            sums += (int) c;
        }
        for (char c : cht) {
            sumt += (int) c;
        }
        return (char) (sumt - sums);
    }

    //利用位运算。其中异或 （相同为0，不同为1） time:O(n) space:O(1)
    public char findTheDifference3(String s, String t) {
        char res = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return res;
    }


    public static void main(String[] args) {
        char c = 'd';
        c ^= 'a';
        c ^= 'a';
        System.out.println(c);
    }
}
