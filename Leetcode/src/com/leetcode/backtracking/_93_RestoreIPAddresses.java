package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _93_RestoreIPAddresses {

    /**
     * 93. Restore IP Addresses
     * When: 2019/05/02
     *
     * solution:
     * 利用回溯法，相当于列举全部情况 然后有些可以舍去。并且注意边界条件！
     *
     * time: O(3^4) => O(1)
     * space: O(n)
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(res, s, 0, "", 0);
        return res;
    }

    public static void helper(List<String> res, String s, int index, String ret, int count) {
        if (count > 4) return; // 表示超越了其ip的最多三位的特征
        if (count == 4 && index == s.length()) {
            res.add(ret);
            return;
        }

        // 表示最多取三位
        for (int i = 1; i < 4; i++) {
            // 超出了总长度
            if (index + i > s.length()) break;
            String temp = s.substring(index, index + i);
            // 不满足的ip规范的情况
            if ((temp.startsWith("0") && temp.length() > 1 ) || (i == 3) && Integer.parseInt(temp) >= 256) continue;
            // 这里的count 表示前面有几个数，如果是前面有三个最后一个肯定就不需要"." 而是直接 ""
            helper(res, s, index + i, ret + temp + (count == 3 ? "" : "."), count + 1);
        }
    }


    public static void main(String[] args) {
        List<String> list  = restoreIpAddresses("25525511135");
        for (String s: list) {
            System.out.println(s);
        }
    }
}
