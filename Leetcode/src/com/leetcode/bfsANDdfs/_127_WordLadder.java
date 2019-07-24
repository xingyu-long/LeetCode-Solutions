package com.leetcode.bfsANDdfs;

import java.util.*;

public class _127_WordLadder {

    /**
     *  127. Word Ladder
     *  When: 2019/06/03
     *  Review1:2019/7/24
     *  估计这里的shortest路径就是仅仅找到的这一条。
     * solution:
     * 利用BFS，寻找那些共同的pattern 变换
     * test case:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     *                hit
     *                |
     *                hot
     *               / \
     *             dot lot
     *             /    |
     *            dog   log
     *            /
     *           cog
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    /**
     提出的问题？
     如何确定相差1，或者高效的方式确定: word的每个位置用'a'-'z'进行循环
     */
    //利用set来保存wordList;
    // 利用set来remove那些用过的
    // time: O(m * n)
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
            for (int i = 0; i <  word.length(); i++) {
                char[] wordUnit = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    wordUnit[i] = j;
                    String temp = new String(wordUnit); // 这里相当于每次拼装成的字符串，然后看set里面是否有，并且看是否等于endWord 这样的话就可以level + 1
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


    // 使用hashMap来记录其level 注意这里equals 成功之后是直接返回level 后面加入节点，set删除用过的
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (set.contains(beginWord)) {
            set.remove(beginWord);
        }
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int curLevel = map.get(word);
            for (int i = 0; i < word.length(); i++) {
                char[] wordUnit = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    wordUnit[i] = j;
                    String temp = new String(wordUnit);
                    if (set.contains(temp)) {
                        if (temp.equals(endWord)) {
                            return curLevel + 1;
                        }
                        map.put(temp, curLevel + 1);
                        queue.offer(temp);
                        set.remove(temp);
                    }
                }
            }
        }
        return 0;
    }
}
