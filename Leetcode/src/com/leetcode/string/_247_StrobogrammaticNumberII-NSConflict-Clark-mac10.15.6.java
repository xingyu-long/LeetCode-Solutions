package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2019/8/6, 05/27/2020
 * @Description: Recursion
 **/
public class _247_StrobogrammaticNumberII {

    // https://www.cnblogs.com/grandyang/p/5200919.html
    // 这个规律没看出来。偶数的情况下，肯定会内部形成 “” 然后是00，11，69，88，96 成对出现。
    // 如果是奇数的话， 会生成“0”， “1”，“8” 然后再外部包括
    // 主要是处理0的情况没有想好
    public static List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    public static List<String> helper(int n, int m) {
        if (n == 0) {
            return new ArrayList<>(Arrays.asList(""));
        }
        if (n == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        }

        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (n != m) { // 相当于内部还是可以放的时候
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> res = findStrobogrammatic(4);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
