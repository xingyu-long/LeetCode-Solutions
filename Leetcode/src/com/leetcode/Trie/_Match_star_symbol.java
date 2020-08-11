package com.leetcode.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date: 07/19/2020
 * @Description: TODO
 **/
public class _Match_star_symbol {

    class Trie {

        Trie[] child;
        String word;
        boolean isWord;

        public Trie() {
            child = new Trie[26]; // 0 -> 'a', 25 -> 'z'.
            word = "";
            isWord = false;
        }
    }

    private Trie root = new Trie();

    private void addWord(String word) {
        Trie curr = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (curr.child[index] == null) {
                curr.child[index] = new Trie();
            }
            curr = curr.child[index];
        }
        // mark as word.
        curr.isWord = true;
        curr.word = word;
    }

    private boolean startsWith(String word) {
        Trie curr = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (curr.child[index] == null) {
                return false;
            } else {
                curr = curr.child[index];
            }
        }
        return true;
    }

    private List<String> search(String pattern) {
        Set<String> set = new HashSet<>();
        find(root, pattern, set);
        return new ArrayList<>(set);
    }

    private void find(Trie curr, String pattern, Set<String> ans) {
        if (pattern.length() == 0) {
            if (curr.isWord) {
                ans.add(curr.word);
            }
            return;
        }
        char ch = pattern.charAt(0);
        if (ch == '*') { // use '*' as single one char.
            find(curr, pattern.substring(1), ans); // skip *
        }
        // 找寻可以走的下一层的点。
        for (int i = 0; i < 26; i++) {
            char next = (char) ('a' + i);
            if (curr.child[i] != null) {
                if (ch == '*') {
                    find(curr.child[i], pattern, ans); // use '*' as multiple char.
                }
                // 这个表示ch 不是 * 然后，可以走到下一层的情况。
                if (ch != '*' && next == ch) {
                    find(curr.child[i], pattern.substring(1), ans);
                }
            }
        }
    }

    static public void main(String args[]) {
        _Match_star_symbol main = new _Match_star_symbol();
        main.addWord("google");
        main.addWord("apple");
        main.addWord("amazon");
        main.addWord("facebook");
        main.addWord("good");
        main.addWord("luck");
        main.addWord("le");
        main.addWord("tle");

        System.out.println(main.search("a*on"));
        System.out.println(main.search("*le"));
        System.out.println(main.search("*le*"));
        System.out.println(main.startsWith("ba"));
    }

}
