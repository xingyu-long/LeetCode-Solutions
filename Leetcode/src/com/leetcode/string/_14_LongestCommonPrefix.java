/*
 * @Date: 12/03/2019 13:09:47
 * @LastEditTime: 06/05/2022 17:27:18
 * @Description: String Matching
 */
package com.leetcode.string;

public class _14_LongestCommonPrefix {

    // 1. 首先选中第一个作为子串，
    // 2. 使用后面的循环str[i]是否包含（使用indexOf）包含最低的情况就是indexOf 返回0
    // 3. 不包含就减少一位 （这里要用while 而不是if）
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
                // 超过最短情况肯定就返回
                // i + 1是strs[0]当前的长度
                if (i + 1 > strs[j].length() || strs[j].charAt(i) != c) {
                    return res;
                }
            }
            res += Character.toString(c);
        }
        return res;
    }

    // time: O(m * n) space: O(1)
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            int j;
            for (j = 1; j < strs.length && i < strs[j].length(); j++) {
                if (strs[j].charAt(i) != ch) break;
            }
            if (j == strs.length) sb.append(ch);
            if (j != strs.length) break;
        }
        return sb.toString();
    }
}
