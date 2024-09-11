/*
 * @Date: 01/20/2020 15:52:06
 * @LastEditTime: 08/08/2022 16:36:04
 * @Description: 也可以用常规的DFS去搜索里面的值。
 */
package com.leetcode.trie;

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
