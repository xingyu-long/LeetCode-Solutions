package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * _ThePowerSum
 */
public class _ThePowerSum {

    public static List<String> powerSum2(int X, int N) {
        List<String> res = new ArrayList<>();
        generateAll(res, "", X, X, N, 1);
        return res;
    }
    
    public static void generateAll(List<String> res, String s, int sum, int X, int N, int start) {
        if (sum == 0) {
            res.add(s);
            return;
        }

        for (int i = start; i <= (int) Math.pow(X, 1.0/N); i++) {
            if (sum >= Math.pow(i, N)) {
                generateAll(res, s + " "+ i, sum - (int) Math.pow(i, N), X, N, i + 1);
            }
        }
    }
    public static void main(String[] args) {
        // List<String> strs = powerSum2(800, 2);
        // System.out.println(strs.size());
        // for (String str : strs) {
        //     System.out.println(str);
        // }
    }
}