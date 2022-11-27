package com.leetcode.trie;

public class _2416_SumofPrefixScoresofStrings {
    class Solution {
        class TrieNode {
            TrieNode[] child;
            int count;
            
            public TrieNode() {
                child = new TrieNode[26];
                count = 0;
            }
        }
        // time: O(n * len(word))
        public int[] sumPrefixScores(String[] words) {
            // build the trie and keep counting
            if (words == null || words.length == 0) {
                return new int[]{};
            }
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode curr = root;
                for (char ch : word.toCharArray()) {
                    int index = ch - 'a';
                    if (curr.child[index] == null) {
                        curr.child[index] = new TrieNode();
                    }
                    // add count to next level
                    curr.child[index].count++;
                    curr = curr.child[index];
                }
            }
            
            int[] res = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                TrieNode curr = root;
                for (char ch : words[i].toCharArray()) {
                    int index = ch - 'a';
                    res[i] += curr.child[index].count;
                    curr = curr.child[index];
                }
            }
            return res;
        }
    }
}
