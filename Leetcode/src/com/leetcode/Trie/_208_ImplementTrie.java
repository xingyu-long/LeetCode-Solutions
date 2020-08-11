package com.leetcode.Trie;

public class _208_ImplementTrie {

    /**
     *  208. Implement Trie (Prefix Tree)
        When: 2019/7/3
     *
     *
     */

    /**
     * Define the TrieNode
     */
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

    /**
     * Initialize your data structure here.
     */
    public _208_ImplementTrie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
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

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int position = word.charAt(i) - 'a';
            if (cur.children[position] == null) return false;
            cur = cur.children[position];
        }
        return cur.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int position = prefix.charAt(i) - 'a';
            if (cur.children[position] == null) return false;
            cur = cur.children[position];
        }
        return true;
    }


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
