package com.leetcode.Trie;

import java.util.ArrayList;
import java.util.List;

public class _212_WordSearchII {

    public class TrieNode {
        TrieNode[] child;
        boolean isWord;
        String str;

        public TrieNode() {
            child = new TrieNode[26];
            isWord = false;
            str = "";
        }
    }

    // 返回值写成了 root。。。。
    // child【index】 写错了
    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (curr.child[index] == null) curr.child[index] = new TrieNode();
                curr = curr.child[index];
            }
            curr.isWord = true;
            curr.str = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        // 利用words 构建trie，然后遍历baord中每一个元素 进行dfs看是否符合trie里面的，构成单词
        // m*n*len(word)*#of word -> m*n*max(len(word))
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return new ArrayList<>();
        if (words == null || words.length == 0) return new ArrayList<>();

        TrieNode root = buildTrie(words);

        List<String> res = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(res, root, i, j, board, visited);
            }
        }
        return res;
    }
    // [["a","a"]]
    //["aaa"] 所以需要visited数组
    public void dfs(List<String> res, TrieNode root, int i, int j, char[][] board, boolean[][] visited) {
        // 这里root查找是否有就当做了visited数组
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (visited[i][j]) return;
        char ch = board[i][j];
        int index = ch - 'a';
        if (root.child[index] == null) return;
        root = root.child[index];
        // 一定需要放在取child之后：因为每次加入点的时候总是后一步。
        // [["a"]], ["a"] 
        if (root.isWord) {
            res.add(root.str);
            root.isWord = false;
        }
        visited[i][j] = true;
        dfs(res, root, i + 1, j, board, visited);
        dfs(res, root, i - 1, j, board, visited);
        dfs(res, root, i, j + 1, board, visited);
        dfs(res, root, i, j - 1, board, visited);
        visited[i][j] = false;
    }
}
