package com.leetcode.dynamicProgramming;

import java.util.List;

public class _120_Triangle {

    /**
     * 120. Triangle
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers
     * on the row below.

         For example, given the following triangle
         [
         [2],
         [3,4],
         [6,5,7],
         [4,1,8,3]
         ]
         The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

         第i层 : j
         第i + 1层 : j, j + 1

         test case:
         倒着来
         res = [0, 0, 0, 0, 0]
        i = 3: [4, 1, 8, 3, 0]
               [6, 5, 7]
        i = 2: [7, 6, 10, 3, 0]
               [3, 4]
        i = 1: [9, 10, 10, 3, 0]
               [2]
        i = 0: [11, 10, 10, 3, 0]
        return res[0] = 11
     * @param triangle
     * @return
     */
    // time: (n^2) space: O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] res = new int[triangle.size() + 1]; //这里是因为后面有个j+1 防止溢出
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // j = 0这时候相当于赋值（初始值为0）
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }
}