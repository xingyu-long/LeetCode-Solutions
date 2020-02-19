package com.leetcode.Trie;

import java.util.HashMap;

public class _677_MapSumPairs {
    public class TrieNode {
        TrieNode[] child;
        int sum;
        public TrieNode() {
            child = new TrieNode[128];
            sum = 0;
        }
    }
    TrieNode root;
    HashMap<String, Integer> map;
    /** Initialize your data structure here. */
    public _677_MapSumPairs() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    //time:O(len(key))
    public void insert(String key, int val) {
        int diff = val - map.getOrDefault(key, 0);
        TrieNode curr = root;
        for (char ch : key.toCharArray()) {
            if (curr.child[ch] == null) {
                curr.child[ch] = new TrieNode();
            }
            curr.child[ch].sum += diff;
            curr = curr.child[ch];
        }
        map.put(key, val);
    }

    // time:O(len(prefix))
    public int sum(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            if (curr.child[ch] == null) return 0;
            curr = curr.child[ch];
        }
        return curr.sum;
    }
}
