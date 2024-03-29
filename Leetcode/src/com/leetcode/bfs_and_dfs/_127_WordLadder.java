package com.leetcode.bfs_and_dfs;

import java.util.*;

/**
 * @Date: 2019/06/03, 2019/7/24, 04/15/2020, 6/5/2020
 * @Description: BFS
 **/
public class _127_WordLadder {

    //利用set来保存wordList;
    // 利用set来remove那些用过的
    // time: O(len(word) * 26 * wordList)
    // space: O(n) (set and queue)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (set.contains(beginWord)) {
            set.remove(beginWord);
        }
        Queue<String> queue = new LinkedList<>();
        int level = 1;
        int curNum = 1; // 表示当前这一层的节点个数
        int nextNum = 0; // 表示下一层的节点个数
        // 一开始需要把beginWord放入进去
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            curNum--;
            for (int i = 0; i < word.length(); i++) {
                char[] wordUnit = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    wordUnit[i] = j;
                    String temp = new String(
                        wordUnit); // 这里相当于每次拼装成的字符串，然后看set里面是否有，并且看是否等于endWord 这样的话就可以level + 1
                    if (set.contains(temp)) {
                        if (temp.equals(endWord)) {
                            return level + 1; //这里的加1 是把最后的元素加入
                        }
                        nextNum++;
                        queue.offer(temp);
                        set.remove(temp);
                    }
                }
            }
            // 相当于一层只有一个元素，前面又被减为0 所以后面操作的就是下一层，则curNum就等于nextNum
            // cur以及nextNum主要是用来方便记录level
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return 0;
    }


    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        dict.remove(beginWord);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {  
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    char[] chs = curr.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chs[j] = ch;
                        String temp = new String(chs);
                        if (dict.contains(temp)) {
                            if (temp.equals(endWord)) {
                                return level + 1;
                            }
                            queue.offer(temp);
                            dict.remove(temp);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    // Bidirectional BFS
    // q1, q2轮流换，q1用来向前尝试更换，并且加入到q中，如果q1.size() > q2.size()
    // 则会q1，q2交换，中间用q2来验证是否走到了有相同的词语。
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        dict.remove(beginWord);

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();

        q1.add(beginWord);
        q2.add(endWord);

        int res = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {
            res++;
            // swap
            if (q1.size() > q2.size()) {
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }

            Set<String> q = new HashSet<>();
            for (String word : q1) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char c = chs[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chs[i] = ch;
                        String s = new String(chs);
                        if (q2.contains(s)) {
                            return res + 1;
                        }
                        if (!dict.contains(s)) {
                            continue;
                        }
                        dict.remove(s);
                        q.add(s);
                    }
                    chs[i] = c;
                }
            }
            q1 = q;
        }
        return 0;
    }
}
