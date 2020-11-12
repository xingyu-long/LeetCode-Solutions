package com.leetcode.array.counter;

import java.util.HashMap;

/**
 * @Date: 02/24/2020, 09/02/2020
 * @Description: Sliding Window
 **/
public class _904_FruitIntoBaskets {

    // time:O(n) space:O(2)
    // sliding window
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0)
            return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int k = 2, count = 0;
        int start = 0, end = 0, n = tree.length;
        int res = 0;
        while (end < n) {
            if (map.get(tree[end]) == null)
                count++;
            map.put(tree[end], map.getOrDefault(tree[end], 0) + 1);
            while (count > k) {
                map.put(tree[start], map.get(tree[start]) - 1);
                if (map.get(tree[start]) == 0) {
                    count--;
                    map.remove(tree[start]);
                }
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}
