package com.leetcode.bfsANDdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1087_BraceExpansion {
    // Input: "{a,b}c{d,e}f"
    // Output: ["acdf","acef","bcdf","bcef"]
    // 没有多层{}
    public String[] expand(String S) {
        if (S == null || S.length() == 0) return new String[0];
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
            while (j < s.length() && s.charAt(j) != '}') j++;
            String [] candidates = s.substring(index + 1, j).split(",");
            for (String candidate : candidates) {
//                System.out.println("in -> " + candidate);
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

    public static void main(String[] args) {
        _1087_BraceExpansion braceExpansion = new _1087_BraceExpansion();
        String s = "{a,b,c}d{e,f}";
        String[] res = braceExpansion.expand(s);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
