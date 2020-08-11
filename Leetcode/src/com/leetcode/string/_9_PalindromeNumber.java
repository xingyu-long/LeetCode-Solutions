package com.leetcode.string;

public class _9_PalindromeNumber {

    /**
     *  9. Palindrome Number
     *  When:2019/7/21
     *  Difficulty: Easy
     *
     * @param x
     * @return
     */
    //time:O(n) space:O(1)
    public boolean isPalindrome(int x) {
        //后面这个条件是怎么想出来的？？？？
        // 应该要考虑overflow的情况！！！
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int res = 0;
        int temp = x;
        while (x != 0) {
            res = res * 10 + x % 10;
            if (res > Integer.MAX_VALUE) return false;
            x = x / 10;
        }
        return res == temp;
    }


    // https://www.bilibili.com/video/av51692387/?p=6
    // 前后减掉的操作
}
