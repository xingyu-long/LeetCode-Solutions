package com.leetcode.string;

import java.util.HashMap;

/**
 * @Date: 2019/8/6, 05/27/2020
 * @Description: Map, two pointer
 **/
public class _246_StrobogrammaticNumber {

    // 可以看做回文数的变形
    // time:O(n) space:O(n)
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        int lo = 0, hi = num.length() - 1;
        while (lo < hi) {
            if (!map.containsKey(num.charAt(lo))) {
                return false;
            }
            if (map.get(num.charAt(lo)) != num.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }
}