package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _119_PascalsTriangleII {

    /**
     * LeetCode 119. Pascal's Triangle II
     * When: 2019/03/16
     *
     * 思路：与之前一样 但是输出不同
     *
     * 涉及到的数据结构或者方法：ArrayList(), List<>
     * 是否有更加高效的办法？？？？
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();

        int numRows = rowIndex + 1;
        List<Integer> list = new ArrayList<>();
        // 这里应该返回空数组并非null值
        if (rowIndex < 0) return list;
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
        return res.get(rowIndex);
    }

    public static void main(String[] args){
        System.out.println(getRow(3));
    }
}