package com.leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 05/08/2020
 * @Description: Trie
 **/
public class _1268_SearchSuggestionsSystem {
    class Trie{
        Trie[] child;
        List<String> topList;

        public Trie() {
            child = new Trie[26];
            topList = new ArrayList<>();
        }
    }
    // Time: O(m * n * logn + L), space: O(m * n + L * m)
    // - including return list ans, where
    // m = average length of products, n = products.length,
    // L = searchWord.length().
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // 我感觉使用Trie来做 可以先想一下结构
        Arrays.sort(products);
        Trie root = buildTrie(products);
        List<List<String>> res = new ArrayList<>();
        Trie curr = root;
        for (char ch : searchWord.toCharArray()) {
            int index = ch - 'a';
            if (curr != null) {
                curr = curr.child[index];
            }
            res.add(curr == null ? Arrays.asList() : curr.topList);
        }
        return res;
    }

    public Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie curr = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (curr.child[index] == null) {
                    curr.child[index] = new Trie();
                }
                curr = curr.child[index];
                if (curr.topList.size() < 3)
                    curr.topList.add(word);
            }
        }
        return root;
    }
}
