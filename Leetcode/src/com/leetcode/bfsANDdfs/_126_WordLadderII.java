package com.leetcode.bfsANDdfs;

import java.util.*;

public class _126_WordLadderII {

    /**
     * 126. Word Ladder II
     * When:11/1/2019
     * Difficulty: Hard
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList == null || wordList.size() == 0) return res;
        if (!wordList.contains(endWord)) return res;

        int curNum = 1;
        int nextNum = 0;
        boolean found = false;

        HashSet<String> set = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();
        HashMap<String, List<String>> map = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            curNum--;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    sb.setCharAt(i, ch);
                    String newWord = sb.toString();
                    if (set.contains(newWord)) {
                        if (visited.add(newWord)) {
                            nextNum++;
                            queue.offer(newWord);
                        }
                        if (map.containsKey(newWord)) {
                            map.get(newWord).add(word);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            map.put(newWord, list);
                        }
                        if (newWord.equals(endWord)) {
                            found = true;
                        }
                    }
                }
            }
            if (curNum == 0) {
                if (found) break;
                curNum = nextNum;
                nextNum = 0;
                set.removeAll(visited);
                visited.clear();
            }
        }
        // for (String key : map.keySet()) {
        //     for (String ans : map.get(key)) {
        //         System.out.print("key =" + key + " val = " + ans + " ");
        //     }
        //     System.out.println();
        // }
        // 已构建好map里面的关系
        dfs(res, new ArrayList<>(), map, endWord, beginWord);
        return res;
    }

    // 从后往前找。
    public void dfs(List<List<String>> res, List<String> list, HashMap<String, List<String>> map, String endWord, String beginWord) {
        if (endWord.equals(beginWord)) {
            list.add(0, beginWord);
            res.add(new ArrayList<>(list));
            list.remove(0);
            return;
        }
        list.add(0, endWord);
        if (map.get(endWord) != null) {
            for (String s : map.get(endWord)) {
                dfs(res, list, map, s, beginWord);
            }
        }
        list.remove(0);
    }
}
