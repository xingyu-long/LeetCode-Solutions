package com.leetcode.Trie;

import java.util.ArrayList;
import java.util.List;

public class _212_WordSearchII {

    class TrieNode {
        TrieNode[] child;
        boolean isWord;
        String word;

        public TrieNode() {
            child = new TrieNode[26];
            isWord = false;
            word = null;
        }
    }

    /**
     * 212. Word Search II
     * When:2019/9/28
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 ||
                board[0].length == 0 || board[0] == null) return res;
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, root, board, res);
            }
        }
        return res;
    }

    public void dfs(int i, int j, TrieNode node, char[][] board, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '#' || node.child[c - 'a'] == null) return;
        node = node.child[c - 'a']; // 这个相当于是下一层的操作
        if (node.isWord) {
            res.add(node.word);
            node.isWord = false; // 不能重复用，也只能被找到一次
            // node.word = null;
        }
        board[i][j] = '#';
        dfs(i+1, j, node, board, res);
        dfs(i-1, j, node, board, res);
        dfs(i, j+1, node, board, res);
        dfs(i, j-1, node, board, res);
        board[i][j] = c;
    }

    // build the trie tree
    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int pos = c - 'a';
                if (cur.child[pos] == null) {
                    cur.child[pos] = new TrieNode();
                }
                cur = cur.child[pos];
            }
            cur.isWord = true;
            cur.word = word;
        }
        return root;
    }
}
