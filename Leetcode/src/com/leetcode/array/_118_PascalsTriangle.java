package com.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2019/03/16 Review1: 2019/7/4 Review2: 2019/7/23, 08/12/2020
 * @Description: greedy, DP
 **/
public class _118_PascalsTriangle {
    
    // time: O(n) space: O(n)
    public List<List<Integer>> generate(int numRows) {
        List<Integer> list = new ArrayList<>();
        List<Integer> curr = null;
        List<List<Integer>> res = new ArrayList<>();
        while (numRows-- > 0) {
            list.add(1);
            curr = new ArrayList<>(list);
            for (int i = 1; i < list.size() - 1; i++) {
                list.set(i, curr.get(i - 1) + curr.get(i));
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }

    // time: O(n * n) space: O(n)
    // list.add(0, 1) will cost O(n)
    public static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1)
            return res;
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

    // recursion with memo. 也可以用dp
    // time:O(n ^ 2 / 2 ~ n^2) space:O(n)
    public List<List<Integer>> generate4(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0)
            return res;
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
        if (map.containsKey(key))
            return map.get(key);
        int val;
        if (j == 0 || j == i) {
            val = 1;// 表示在每行的第一个和最后一个都是1
        } else {
            val = calculate(i - 1, j - 1, map) + calculate(i - 1, j, map);
        }
        map.put(key, val);
        return val;
    }
}
