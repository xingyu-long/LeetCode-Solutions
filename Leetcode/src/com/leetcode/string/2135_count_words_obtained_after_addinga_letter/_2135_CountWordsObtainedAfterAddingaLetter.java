/*
 * @Date: 07/19/2022 14:20:08
 * @LastEditTime: 07/19/2022 15:24:05
 * @Description: Sorting
 */
package com.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _2135_CountWordsObtainedAfterAddingaLetter {
    // time: O(n * sorting(word) + n * n)
    public int wordCount(String[] startWords, String[] targetWords) {
        // sorting and try it out
        int res = 0;
        Set<String> dict = new HashSet<>();
        for (String word : startWords) {
            char[] chs = word.toCharArray();
            Arrays.sort(chs);
            dict.add(new String(chs));
        }
        /*
         * word = gj temp = j
         * word = gj temp = g
         * word = fov temp = fv
         * 对于每一个targetWord一旦有符合的算一个即可，这是为什么后面用break
         */
        for (String word : targetWords) {
            char[] chs = word.toCharArray();
            Arrays.sort(chs);
            String s = new String(chs);
            for (int i = 0; i < s.length(); i++) {
                String temp = s.substring(0, i) + s.substring(i + 1);
                if (dict.contains(temp)) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    public class Trie {
        Trie[] child;
        boolean isWord;
        
        public Trie() {
            child = new Trie[26];
            isWord = false;
        }
    }
    
    public int wordCount2(String[] startWords, String[] targetWords) {
        Trie root = buildTrie(startWords);
        int res = 0;
        for (String word : targetWords) {
            char[] chs = word.toCharArray();
            Arrays.sort(chs);
            // System.out.println("try = " + new String(chs));
            if (isValid(root, new String(chs), 0, false)) {
                // System.out.println("target = " + new String(chs));
                res++;
            }
        }
        return res;
    }
    
    public boolean isValid(Trie root, String word, int index, boolean isSkipped) {
        if (index == word.length()) {
            return root.isWord && isSkipped;
        }
        int nextIndex = word.charAt(index) - 'a';
        Trie[] child = root.child;
        return (child[nextIndex] != null ? isValid(child[nextIndex], word, index + 1, isSkipped) : false) || 
         (isSkipped ? false : isValid(root, word, index + 1, true));
        
    }
    
    public Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie curr = root;
            char[] chs = word.toCharArray();
            Arrays.sort(chs);
            // System.out.println("build - " + new String(chs));
            for (char ch : chs) {
                int index = ch - 'a';
                Trie[] child = curr.child;
                if (child[index] == null) {
                    child[index] = new Trie();
                }
                curr = child[index];
            }
            curr.isWord = true;
        }
        return root;
    }
}
