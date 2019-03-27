package com.leetcode;

import java.util.HashMap;

public class _205_IsomorphicStrings {

    /**
     * 205. Isomorphic Strings
     * When: 2019/03/25
     *
     * solution：
     * 利用hashmap来记忆其每个对应，如果都是对应的说明结构相同。
     *
     * 涉及到的数据结构或者方法：
     * HashMap作为映射
     * hashMap
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return true;
        HashMap<Character, Character> map = new HashMap<>();

        //默认s和t应该是同长度 不然也没有比的必要
        for (int i = 0; i < s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)){
                // 则表示结构相同
                if (map.get(a).equals(b)) continue;
                else return false;
            }else {
                // 没有b变量这个value才插入，因为这样才确保 唯一的对应性
                if (!map.containsValue(b)){
                    map.put(a, b);
                } else return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String s = "ab";
        String t = "aa";
        System.out.println(isIsomorphic(s, t));
    }
}
