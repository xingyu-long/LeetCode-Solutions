package com.leetcode.Trie;

/**
 * @Date: 2019/7/3, 05/13/2020
 * @Description: Trie
 **/
public class _208_ImplementTrie {

    class TrieNode {

        TrieNode[] children;
        boolean isWord;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
            word = "";
        }
    }

    private TrieNode root;

    public _208_ImplementTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int position = word.charAt(i) - 'a';
            if (cur.children[position] == null) {
                cur.children[position] = new TrieNode();
            }
            cur = cur.children[position];
        }
        cur.isWord = true;
        cur.word = word;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int position = word.charAt(i) - 'a';
            if (cur.children[position] == null) {
                return false;
            }
            cur = cur.children[position];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int position = prefix.charAt(i) - 'a';
            if (cur.children[position] == null) {
                return false;
            }
            cur = cur.children[position];
        }
        return true;
    }
}
