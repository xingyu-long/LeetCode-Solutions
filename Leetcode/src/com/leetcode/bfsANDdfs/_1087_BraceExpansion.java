package com.leetcode.bfsANDdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Date: 08/12/2020
 * @Description: DFS
 **/
public class _1087_BraceExpansion {
    // Input: "{a,b}c{d,e}f"
    // Output: ["acdf","acef","bcdf","bcef"]
    // 没有多层{}
    public String[] expand(String S) {
        if (S == null || S.length() == 0)
            return new String[0];
        List<String> res = new ArrayList<>();
        dfs(res, S, 0, new StringBuilder());
        Collections.sort(res);
        return res.toArray(new String[0]);
    }

    public void dfs(List<String> res, String s, int index, StringBuilder sb) {
        if (index >= s.length()) {
            res.add(sb.toString());
            return;
        }
        // 利用backtracking
        if (s.charAt(index) == '{') {
            // find the '}'
            int j = index + 1;
            while (j < s.length() && s.charAt(j) != '}')
                j++;
            String[] candidates = s.substring(index + 1, j).split(",");
            for (String candidate : candidates) {
                // System.out.println("in -> " + candidate);
                sb.append(candidate);
                dfs(res, s, j + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            sb.append(s.charAt(index));
            dfs(res, s, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 带有返回值的dfs
    public String[] expand2(String S) {
        if (S == null || S.length() == 0) {
            return new String[] {};
        }
        List<String> list = dfs(S, 0);
        Collections.sort(list);
        String[] res = new String[list.size()];
        return list.toArray(res);
    }

    public List<String> dfs(String s, int index) {
        List<String> res = new ArrayList<>();
        if (index == s.length()) {
            res.add("");
            return res;
        } else {
            if (s.charAt(index) == '{') {
                int j = index + 1;
                for (; j < s.length(); j++) {
                    if (s.charAt(j) == '}')
                        break;
                }
                String[] strs = s.substring(index + 1, j).split(",");
                List<String> next = dfs(s, j + 1);
                for (String str : strs) {
                    for (String n : next) {
                        String temp = str + n;
                        res.add(temp);
                    }
                }
                return res;
            } else {
                List<String> next = dfs(s, index + 1);
                for (String n : next) {
                    String temp = s.charAt(index) + n;
                    res.add(temp);
                }
                return res;
            }
        }
    }

    public static void main(String[] args) {
        _1087_BraceExpansion braceExpansion = new _1087_BraceExpansion();
        String s = "{a,b,c}d{e,f}";
        String[] res = braceExpansion.expand(s);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
