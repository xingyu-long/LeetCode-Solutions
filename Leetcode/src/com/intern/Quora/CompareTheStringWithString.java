package com.intern.Quora;

import java.util.HashMap;

public class CompareTheStringWithString {
    /**
     * compare两个string，只有小写字母。
     * 每个stirng内部可以任意换位置，所以位置不重要。
     * 每个 string内部两个letter出现的频率也可以互换，所以这题只需要两个string每个frequency出现的
     * 次数要一样。比如“babzccc” 和 “bbazzcz” 就返回“true”，
     * 因为z和c可以互换频率。 但是 “babzcccm” 和 “bbazzczl”
     * 就不一样，因为m在第一个里出现过，第二个里没有出现过。
     * If two strings are close enough.
     */
    // 1. 先看出现的种类 利用map.keySet() 这里比较的是character
    // 2. 利用map的value进行freq统计
    public static boolean compareString(String s1, String s2) {
        // 如果长度不同肯定组成也不同
        if (s1.length() != s2.length()) return false;
        // 字母出现的种类要一样
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for (char ch : s1.toCharArray()) {
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }

        for (char ch : s2.toCharArray()) {
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }

        for (char key : map1.keySet()) {
            if (!map2.containsKey(key)) return false;
        }

        for (char key : map2.keySet()) {
            if (!map1.containsKey(key)) return false;
        }
        // 字母出现的频率的统计要相同 例如babzccc （1a2b1z3c），bbazzcz(1a2b3z1c)
        // 这里尽管不同，但是前面的系数都是相同的。
        HashMap<Integer, Integer> count1 = new HashMap<>();
        HashMap<Integer, Integer> count2 = new HashMap<>();
        for (char key : map1.keySet()) {
            int freq = map1.get(key);
            count1.put(freq, count1.getOrDefault(freq, 0) + 1);
        }

        for (char key : map2.keySet()) {
            int freq = map2.get(key);
            count2.put(freq, count2.getOrDefault(freq, 0) + 1);
        }

        for (int freq : count1.keySet()) {
            if (!count1.get(freq).equals(count2.get(freq))) {
                return false;
            }
        }
        return true;
    }
}
