package com.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _118_PascalsTriangle {
    /**
     *  118. Pascal's Triangle
     *  When: 2019/03/16
     *  Review1: 2019/7/4
     *  Review2: 2019/7/23
     *  review3: 2019/8/20
     * <p>
     * <p>
     * 思路：就是按照算法演示来相加 保留current以及previous行然后依次计算
     * <p>
     * 涉及到的数据结构或者方法：ArrayList(), List<>
     *
     * @param numRows
     * @return
     */

    // time:O(n^2) space:O(1) // 返回结果不算入space里面。
    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) row.add(1);
                else row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            res.add(row);
        }
        return res;
    }

    /**
     * 更加巧妙的方法
     */
    public static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) return res;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }

    // recursion with memo. 也可以用dpe
    // time:O(n ^ 2 / 2 ~ n^2) space:O(n)
    public List<List<Integer>> generate4(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0) return res;
        HashMap<String, Integer> map = new HashMap<>(); // how to set proper key-value.
        for (int i = 0; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                cur.add(calculate(i, j, map));
            }
            res.add(cur);
        }
        return res;
    }

    public int calculate(int i, int j, HashMap<String, Integer> map) {
        String key = i + "," + j;
        if (map.containsKey(key)) return map.get(key);
        int val;
        if (j == 0 || j == i) {
            val =  1;//表示在每行的第一个和最后一个都是1
        } else {
            val = calculate(i - 1, j - 1, map) + calculate(i - 1, j, map);
        }
        map.put(key, val);
        return val;
    }
}
