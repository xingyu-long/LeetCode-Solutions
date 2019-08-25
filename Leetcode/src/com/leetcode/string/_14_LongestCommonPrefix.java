package com.leetcode.string;

public class _14_LongestCommonPrefix {

    /**
     *  14. Longest Common Prefix
     *  When: 2019/03/12
     *  Review1: 2019/8/3
     *  review2: 2019/8/24
     *
     *  Difficulty: Easy
     *  解题思路：
     *  1. 首先选中第一个作为子串，
     *  2. 使用后面的循环str[i]是否包含（使用indexOf）包含最低的情况就是indexOf 返回0
     *  3. 不包含就减少一位 （这里要用while 而不是if）
     *
     *  涉及到的数据结构或者方法： substring(), indexOf()
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0 || strs == null) return "";
        String res = strs[0];

        for (int i = 1; i < strs.length; i++){
            while (strs[i].indexOf(res) != 0){
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }


    //time:O(m * n) space:O(1)
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) { //从第二个strs String数组开始
                // 超过最短情况肯定就返回了，这个 >= 需要好好理解
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return res;
                }
            }
            res += Character.toString(c);
        }
        return res;
    }

    public static void main(String[] args){
//        System.out.println(longestCommonPrefix(new String[]{"abc","abaaaacd"}));
        String a = "abc";
        String b = "abaaaacd";
        System.out.println(a.indexOf(b));
    }
}
