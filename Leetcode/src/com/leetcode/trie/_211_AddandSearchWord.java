package com.leetcode.trie;

/**
 * @Date: 2019/7/3, 05/11/2020
 * @Description: Trie
 **/
public class _211_AddandSearchWord {

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

    public _211_AddandSearchWord() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int j = word.charAt(i)
                - 'a';// calculate the difference and know what alphabet in there. (eg: b (j = 1), c (j = 3) etc.)
            if (cur.children[j] == null) {
                cur.children[j] = new TrieNode();
            }
            cur = cur.children[j];
        }
        cur.isWord = true;
        cur.word = word;
    }

    public boolean search(String word) {
        return find(word, root, 0);
    }

    public boolean find(String word, TrieNode node, int index) {
        if (index == word.length()) {
            return node.isWord; // 之前把这里给忘记了 最后验证的应该是isWord是否为true
        }
        if (word.charAt(index) == '.') {
            for (TrieNode temp : node.children) {
                if (temp != null && find(word, temp, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int j = word.charAt(index) - 'a';
            TrieNode temp = node.children[j];
            return temp != null && find(word, temp, index + 1);
        }
    }
}
