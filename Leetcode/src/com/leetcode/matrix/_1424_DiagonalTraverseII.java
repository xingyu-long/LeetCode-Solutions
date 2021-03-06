package com.leetcode.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 04/29/2020
 * @Description: Matrix, Traversal
 **/
public class _1424_DiagonalTraverseII {

    // 主要是一条斜线上的r + c的值保持不变（自己写的时候写成了遍历然后check那些不合法的部分，这样浪费了资源）
    // time:O(m * n) space:O(total)
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return new int[]{};
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxKey = 0, n = 0;
        for (int r = nums.size() - 1; r >= 0; r--) {
            for (int c = 0; c < nums.get(r).size(); c++) {
                map.putIfAbsent(r + c, new ArrayList<>());
                map.get(r + c).add(nums.get(r).get(c));
                maxKey = Math.max(maxKey, r + c);
                n++;
            }
        }

        int[] res = new int[n];
        int i = 0;
        for (int key = 0; key <= maxKey; key++) {
            if (map.get(key) == null) {
                continue;
            }
            for (int num : map.get(key)) {
                res[i++] = num;
            }
        }
        return res;
    }
}
