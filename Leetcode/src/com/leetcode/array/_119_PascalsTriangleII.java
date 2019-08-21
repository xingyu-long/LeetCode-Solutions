package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class _119_PascalsTriangleII {

    /**
     * LeetCode 119. Pascal's Triangle II
     * When: 2019/03/16
     * Review1 : 2019/7/4
     * review2 : 2019/8/20
     *
     * 涉及到的数据结构或者方法：ArrayList(), List<>
     *
     * test case:
     * rowIndex=3
     * i = 0; i < 4
     *  (1) i = 0; res = [1,]
     *      未进入第二个循环
     *  (2) i = 1; res = [1, 1, ]
     *      未进入第二个循环
     *  (3) i = 2; res = [1, 1, 1, ];
     *      j = 1; j < 2;
     *      res = [1, 2, 1];
     *  (4) i = 3; res = [1, 1, 2, 1];
     *      j = 1; j < 3;
     *      res = [1,3,2,1]
     *
     *      res = [1,3,3,1]
     *
     * @param rowIndex
     * @return
     */
    // time:O(n^2) space:O(n)
    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0) return res;
        for (int i = 0; i < rowIndex + 1; i++) {
            res.add(0, 1);
            for (int j = 1; j < res.size() - 1; j++) {
                res.set(j, res.get(j) + res.get(j + 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>();
        test.add(0, 1);
        test.add(0, 2);
        for (int in : test) {
            System.out.println(in);
        }
    }
}
