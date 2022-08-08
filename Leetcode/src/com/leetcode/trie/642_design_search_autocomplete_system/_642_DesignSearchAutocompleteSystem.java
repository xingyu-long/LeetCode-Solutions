package com.leetcode.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _642_DesignSearchAutocompleteSystem {
    // 主要是hot那部分需要注意
    public class TrieNode implements Comparable<TrieNode> {
        TrieNode[] children;
        String s;
        int times;
        List<TrieNode> top3;

        public TrieNode() {
            children = new TrieNode[128];
            s = null;
            times = 0;
            top3 = new ArrayList<>();
        }

        @Override
        public int compareTo(TrieNode node) {
            if (this.times == node.times) return this.s.compareTo(node.s);
            return node.times - this.times;
        }

        // update top3
        public void update(TrieNode node) {
            if (!this.top3.contains(node)) this.top3.add(node);
            Collections.sort(top3);
            if (top3.size() > 3) top3.remove(top3.size() - 1);
        }
    }
    TrieNode root;
    TrieNode curr;
    StringBuilder sb;

    // main function
    public _642_DesignSearchAutocompleteSystem(String[] sentences, int [] times) {
        root = new TrieNode();
        curr = root;
        sb = new StringBuilder();
        for (int i = 0; i < times.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    public void add(String sentence, int time) {
        TrieNode temp = root;
        List<TrieNode> list = new ArrayList<>();
        for (char ch : sentence.toCharArray()) {
            if (temp.children[ch] == null) {
                temp.children[ch] = new TrieNode();
            }
            temp = temp.children[ch];
            list.add(temp);
        }
        temp.s = sentence;
        temp.times += time;

        for (TrieNode node : list) {
            node.update(temp);// 添加到每次路过的trieNode里面。
        }
    }
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            add(sb.toString(), 1);
            sb = new StringBuilder();
            curr = root;
        }
        sb.append(c);
        if (curr != null) {
            curr = curr.children[c];
        }
        if (curr == null) return res;

        for (TrieNode node : curr.top3) {
            res.add(node.s);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"i love you", "island", "ironman", "i love leetcode"};
        int[] times = new int[]{5,3,2,2};
        _642_DesignSearchAutocompleteSystem auto = new _642_DesignSearchAutocompleteSystem(strs, times);

        List<String> res = auto.input('i');
        for (String str : res) {
            System.out.println(str);
        }
    }
}
