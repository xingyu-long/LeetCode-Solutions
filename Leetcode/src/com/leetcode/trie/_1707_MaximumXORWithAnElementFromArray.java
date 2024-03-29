/*
 * @Date: 01/02/2021 12:29:54
 * @LastEditTime: 08/08/2022 16:36:10
 * @Description: Similar with 421
 */
package com.leetcode.trie;

import java.util.Arrays;

public class _1707_MaximumXORWithAnElementFromArray {
    class TrieNode {
        TrieNode[] child;

        public TrieNode() {
            child = new TrieNode[2];
        }
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        // 如何判断比选定的小呢?
        // 排序，边构建trie边开始搜索。
        int[] res = new int[queries.length];
        int[][] newQ = new int[queries.length][queries[0].length + 1];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            newQ[i][0] = q[0];
            newQ[i][1] = q[1];
            newQ[i][2] = i;
        }
        Arrays.sort(newQ, (a, b) -> (a[1] - b[1]));
        Arrays.sort(nums);
        int k = 0;
        TrieNode root = new TrieNode();
        for (int i = 0; i < newQ.length; i++) {
            // 先构建
            int[] q = newQ[i];
            int num = q[0], max = q[1];
            // 忘记写<=max
            while (k < nums.length && nums[k] <= max) {
                TrieNode curr = root;
                for (int j = 31; j >= 0; j--) {
                    int index = (nums[k] >> j) & 1;
                    if (curr.child[index] == null) {
                        curr.child[index] = new TrieNode();
                    }
                    curr = curr.child[index];
                }
                k++;
            }
            if (k == 0) {
                res[q[2]] = -1;
                continue;
            }
            // 开始搜索当前的trie
            TrieNode curr = root;
            int currRes = 0;
            for (int j = 31; j >= 0; j--) {
                int index = (num >> j) & 1;
                int expect = 1 - index;
                if (curr.child[expect] != null) {
                    curr = curr.child[expect];
                    currRes = currRes * 2 + 1;
                } else {
                    curr = curr.child[index];
                    currRes = currRes * 2 + 0;
                }
            }
            res[q[2]] = currRes;
        }
        return res;
    }
}
