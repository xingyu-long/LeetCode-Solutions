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

        // 一定要用双set,防止其多个对应一个的时候 出现的加入队列错误
        HashSet<String> set = new HashSet<>(wordList); // 在当前结束之后的情况！其实也是最后一层的时候endWord不会被删除掉
        HashSet<String> visited = new HashSet<>(); // 这个set是为了保持现状用了哪些。
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
        // 记住前者是add在第零个位置，也需要在这里删除。
        list.remove(0);
    }


    // 正向构建
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return new ArrayList<>();
        // traversal and built the graph
        HashSet<String> set = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        set.remove(beginWord);
        HashMap<String, List<String>> map = new HashMap<>();
        int curNum = 1;
        int nextNum = 0;
        boolean found = false;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            curNum--;
            for (int i = 0; i < cur.length(); i++) {
                char[] chs = cur.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    chs[i] = c;
                    String newStr = new String(chs);
                    if (set.contains(newStr)) {
                        if (visited.add(newStr)) {
                            queue.offer(newStr);
                            nextNum++;
                        }
                        if (!map.containsKey(cur)) {
                            List<String> list = new ArrayList<>();
                            list.add(newStr);
                            map.put(cur, list);
                        } else {
                            map.get(cur).add(newStr);
                        }

                        if (newStr.equals(endWord)) {
                            // 还是得利用curNum来判断结束。
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
        dfs2(res,new ArrayList<>(), beginWord, endWord, map);
        return res;
    }

    public void dfs2(List<List<String>> res, List<String> list, String beginWord, String endWord, HashMap<String, List<String>> map) {
        if (beginWord.equals(endWord)) {
            list.add(endWord);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(beginWord);
        if (map.get(beginWord) != null) {
            for (String word : map.get(beginWord)) {
                dfs2(res, list, word, endWord, map);
            }
        }
        list.remove(list.size() - 1);
    }
}
