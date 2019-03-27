package com.leetcode;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class _28_ImplementstrStr {
    /**
     * LeetCode No.28 Implement strStr()
     * 解题思路：使用子串比较即可就是从0+needle的长度然后遍历 用equals()
     * 这里的 i <= haystack.length() - needle.length() 是因为 走needle那么长的话 不能走完整个haystack 会溢出
     * 涉及到的数据结构或者方法： substring()
     * when: 2019/03/12
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++){
            if(haystack.substring(i, i + needle.length()).equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args){
        System.out.println(strStr("abcd", "dc"));
        String a = "abcd";
        System.out.println(a.substring(0,2));
    }
}
