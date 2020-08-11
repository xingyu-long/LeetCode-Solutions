package com.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 05/12/2020
 * @Description: Greedy, Index Map
 **/
public class _763_PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        // greedy
        List<Integer> res = new ArrayList<>();
        int[] index = new int[26];
        int n = S.length();
        for (int i = 0; i < n; i++) {
            index[S.charAt(i) - 'a'] = i;
        }
        int begin = 0, end = 0;
        for (int i = 0; i < n; i++) {
            char ch = S.charAt(i);
            end = Math.max(end, index[ch - 'a']);
            if (end == i) {
                // add
                res.add(end - begin + 1);
                begin = i + 1;
            }
        }
        return res;
    }
}
