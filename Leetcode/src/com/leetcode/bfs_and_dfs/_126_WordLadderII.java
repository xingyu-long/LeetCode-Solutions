/*
 * @Date: 08/08/2022 18:48:56
 * @LastEditTime: 08/09/2022 16:06:21
 * @Description: BFS, Graph, DFS
 */
package com.leetcode.bfs_and_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _126_WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();

        if (!dict.contains(endWord)) return new ArrayList<>();
        dict.remove(beginWord);

        Map<String, Set<String>> map = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        boolean finish = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                map.putIfAbsent(curr, new HashSet<>());
                for (int j = 0; j < curr.length(); j++) {
                    char[] chs = curr.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chs[j] = ch;
                        String temp = new String(chs);
                        if (dict.contains(temp)) {
                            // 这里可能会出现已访问的情况，但是不妨碍建立这个graph
                            map.get(curr).add(temp);
                            if (visited.add(temp)) {
                                queue.offer(temp);
                                if (temp.equals(endWord)) {
                                    finish = true;
                                }
                            }
                        }
                    }
                }
            }
            if (finish) break;
            // 删除已经访问过的
            dict.removeAll(visited);
            visited.clear();
        }
        List<List<String>> res = new ArrayList<>();
        build(res, new ArrayList<>(), map, beginWord, endWord);
        return res;
    }

    private void build(List<List<String>> res, List<String> list, Map<String, Set<String>> map, String beginWord, String endWord) {
        if (beginWord.equals(endWord)) {
            list.add(endWord);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        Set<String> nexts = map.get(beginWord);
        if (nexts != null) {
            list.add(beginWord);
            for (String next : nexts) {
                build(res, list, map, next, endWord);
            }
            list.remove(list.size() - 1);
        }
    }
}
