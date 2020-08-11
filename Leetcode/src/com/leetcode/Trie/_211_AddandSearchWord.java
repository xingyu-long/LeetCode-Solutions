package com.leetcode.Trie;

public class _211_AddandSearchWord {


    /**
     *  211. Add and Search Word
     When: 2019/7/3
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
    public _211_AddandSearchWord() {
        root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int j = word.charAt(i) - 'a';// calculate the difference and know what alphabet in there. (eg: b (j = 1), c (j = 3) etc.)
            if (cur.children[j] == null) {
                cur.children[j] = new TrieNode();
            }
            cur = cur.children[j];
        }
        cur.isWord = true;
        cur.word = word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    /**
     * be careful of '.'
     */
    public boolean search(String word) {
        return find(word, root, 0);
    }

    public boolean find(String word, TrieNode node, int index) {
        if (index == word.length()) return node.isWord;
        if (word.charAt(index) == '.') {
            for (TrieNode temp : node.children) {
                if (temp != null && find(word, temp, index + 1)) return true;
            }
            return false;
        } else {
            int j = word.charAt(index) - 'a';
            TrieNode temp = node.children[j];
            return temp != null && find(word, temp, index + 1);
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}
