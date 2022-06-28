package com.leetcode.dynamicProgramming;

import java.util.List;

public class _120_Triangle {

    // 从下往上，最后的结果直接获取
    // time: (n^2) space: O(n)
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] res = new int[triangle.size() + 1]; //这里是因为后面有个j+1 防止溢出
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // j = 0这时候相当于赋值，然后为原有数据
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }

}