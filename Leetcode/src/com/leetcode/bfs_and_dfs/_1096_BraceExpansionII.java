package com.leetcode.bfs_and_dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class _1096_BraceExpansionII {
    // 1096. Brace Expansion II
    public List<String> braceExpansionII(String expression) {
        if (expression == null || expression.length() == 0) return new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        dfs(set, 0, expression, "");
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

    public void dfs(HashSet<String> res, int index, String expression, String cur) {
        if (index == expression.length()) {
            if (cur.length() > 0) res.add(cur);
            return;
        }

        if (expression.charAt(index) == '{') {
            int j = index + 1;
            int count = 1;
            while (!(expression.charAt(j) == '}' && count == 1)) {
                if (expression.charAt(j) == '{') count++;
                else if (expression.charAt(j) == '}') count--;
                j++;
            }
            List<String> innerPart = braceExpansionII(expression.substring(index + 1, j));
            for (String in : innerPart) {
                dfs(res, j + 1, expression, cur + in);
            }
        } else if (expression.charAt(index) == ',') {
            if (cur.length() > 0) res.add(cur);
            dfs(res, index + 1, expression, "");
        } else {
            dfs(res, index + 1, expression, cur + expression.charAt(index));
        }
    }

    public static void main(String[] args) {
        _1096_BraceExpansionII braceExpansionII = new _1096_BraceExpansionII();
        String s = "{a,b}{c,{d,e}}";
        braceExpansionII.braceExpansionII(s);
    }
}
