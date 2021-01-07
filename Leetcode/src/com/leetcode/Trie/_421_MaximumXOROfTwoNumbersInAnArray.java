/*
 * @Date: 01/02/2021 10:36:26
 * @LastEditTime: 01/02/2021 10:37:22
 * @Description: XOR, Trie
 */
package com.leetcode.Trie;

public class _421_MaximumXOROfTwoNumbersInAnArray {
    class TrieNode {
        TrieNode[] child;

        public TrieNode() {
            child = new TrieNode[2];
        }
    }

    public int findMaximumXOR(int[] nums) {
        // 利用trieNode来优化搜索的方式
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TrieNode root = new TrieNode();
        // 构建trie
        for (int num : nums) {
            TrieNode curr = root;
            for (int i = 31; i >= 0; i--) {
                int index = (num >> i) & 1; // 计算每次当前位是0还是1.
                if (curr.child[index] == null) {
                    curr.child[index] = new TrieNode();
                }
                curr = curr.child[index];
            }
        }

        int res = 0;
        for (int num : nums) {
            int currRes = 0;
            TrieNode curr = root;
            for (int i = 31; i >= 0; i--) {
                int index = (num >> i) & 1;
                int expect = 1 - index; // 以为不同的digit 异或才是1
                // 优先考虑是否可以走预期的那条路。
                if (curr.child[expect] != null) {
                    curr = curr.child[expect];
                    currRes = currRes * 2 + 1;
                } else {
                    curr = curr.child[index];
                    currRes = currRes * 2 + 0;
                }
            }
            res = Math.max(res, currRes);
        }
        return res;
    }
}
