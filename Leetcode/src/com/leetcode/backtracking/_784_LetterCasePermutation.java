/*
 * @Date: 02/16/2021 10:32:37
 * @LastEditTime: 02/16/2021 10:33:21
 * @Description: Backtracking
 */
package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _784_LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        // backtracking
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        char[] chs = S.toCharArray();
        List<String> res = new ArrayList<>();
        dfs(chs, 0, res);
        return res;
    }

    private void dfs(char[] chs, int index, List<String> res) {
        if (index == chs.length) {
            res.add(new String(chs));
            return;
        }

        if (chs[index] >= '0' && chs[index] <= '9') {
            dfs(chs, index + 1, res);
        } else {
            chs[index] = Character.toLowerCase(chs[index]);
            dfs(chs, index + 1, res);

            chs[index] = Character.toUpperCase(chs[index]);
            dfs(chs, index + 1, res);
        }
    }
}
