package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _93_RestoreIPAddresses {

    /**
     * 93. Restore IP Addresses
     * When: 2019/05/02
     * review1:10/28/2019
     * solution:
     * 利用回溯法，相当于列举全部情况 然后有些可以舍去。并且注意边界条件！
     * <p>
     * time: O(3^4) => O(1)
     * space: O(n)
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, s, "", 0, 0);
        return res;
    }

    public static void dfs(List<String> res, String s, String cur, int index, int count) {
        if (count > 5) return;
        // 表示有了四个ip段 以及下一个index等于字符串的长度时，这个就结束了。相当于已经走完了
        if (count == 4 && index == s.length()) {
            res.add(cur);
            return;
        }

        for (int i = 1; i < 4; i++) { // 每一次所走的长度是1，2，或者3
            if (index + i > s.length()) break; // 不能走超过字符串的长度
            String temp = s.substring(index, index + i);
            // 以0开头只能长度为1否则不可以，以及如果是三个长度其值大于255则错误。
            if ((temp.startsWith("0") && temp.length() != 1) || temp.length() == 3 && Integer.parseInt(temp) > 255)
                break;
            dfs(res, s, cur + temp + (count == 3 ? "" : "."), index + i, count + 1);
        }
    }


    public static void main(String[] args) {
        List<String> list = restoreIpAddresses("25525511135");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
