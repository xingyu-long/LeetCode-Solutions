package com.goldmanSachs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MatrixGame {

    public static int play(List<List<Integer>> arr) {
        if (arr == null || arr.size() == 0) return 0;
        // 新建数组 然后排序
        int m = arr.size();
        int n = arr.get(0).size();
        int[] max = new int[n];
        for (int j = 0; j < n; j++) {
            int localMax = 0;
            for (int i = 0; i < m; i++) {
                localMax = Math.max(localMax, arr.get(i).get(j));
                max[j] = localMax;
            }
        }
        Arrays.sort(max);
        int res = 0;
        for (int i = n - 1; i >= 0; i = i - 2) {
            res += max[i];
        }
        for (int i = n - 2; i >= 0; i = i - 2) {
            res -= max[i];
        }
        return res;
    }
    public static void main(String []args) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list1.addAll(Arrays.asList(3, 6, 5, 3, 4, 12));
        list2.addAll(Arrays.asList(4, 5, 2, 6, 5, 4));
        list3.addAll(Arrays.asList(7, 4, 10, 9, 7, 3));
        List<List<Integer>> res = new ArrayList<>();
        res.addAll(Arrays.asList(list1,list2, list3));
        System.out.println(play(res));
    }
}
