package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class _118_PascalsTriangle {
    /**
     * LeetCode 118. Pascal's Triangle
     * When: 2019/03/16
     *
     * 思路：就是按照算法演示来相加 保留current以及previous行然后依次计算
     *
     * 涉及到的数据结构或者方法：ArrayList(), List<>
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        // 这里应该返回空数组并非null值
        if (numRows < 1) return res;

        List<Integer> list = new ArrayList<>();

        //赋值第一行
        list.add(0, 1);
        res.add(list);

        // 每一行的中间部分
        for (int i = 1; i < numRows; i++){
            // 一个是当前的行
            List<Integer> current = new ArrayList<>();
            // 添加每一行的第一个元素 1
            current.add(1);

            // 一个表示当前行的前一行
            List<Integer> previous = res.get(i-1);

            //当前属于第几行就有几个元素
            for (int j = 1; j < i; j ++){
                int temp = previous.get(j - 1) + previous.get(j);
                current.add(temp);
            }

            //添加每一行的最后一个元素 1
            current.add(1);
            res.add(current);
    }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(generate(6));
    }
}
