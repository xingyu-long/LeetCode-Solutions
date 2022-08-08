package com.leetcode.bfs_and_dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * @Date: 12/11/2020 10:10:35
 * @LastEditTime: 12/11/2020 10:16:12
 * @Description: Same as 127. Word Ladder
 */

public class _433_MinimumGeneticMutation {
    // 忘记按照level扫描以及level应该是在当前走完之后++。
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String s : bank) {
            set.add(s);
        }
        if (!set.contains(end))
            return -1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        char[] chs = { 'A', 'C', 'G', 'T' };
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String curr = queue.poll();
                for (int i = 0; i < curr.length(); i++) {
                    char[] temp = curr.toCharArray();
                    for (int j = 0; j < chs.length; j++) {
                        temp[i] = chs[j];
                        String newStr = new String(temp);
                        if (set.contains(newStr)) {
                            queue.offer(newStr);
                            set.remove(newStr);
                            if (newStr.equals(end)) {
                                return res + 1;
                            }
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }
}