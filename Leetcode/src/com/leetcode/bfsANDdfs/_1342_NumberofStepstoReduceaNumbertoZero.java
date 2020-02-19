package com.leetcode.bfsANDdfs;

public class _1342_NumberofStepstoReduceaNumbertoZero {
    // better than O(n)(worse case)
    // time:O(n)
    public int numberOfSteps(int num) {
        if (num == 0) return 0;
        if (num % 2 == 0) return 1 + numberOfSteps(num / 2);
        else return 1 + numberOfSteps(num - 1);
    }

    // space:O(1)
    public int numberOfSteps2(int num) {
        if (num == 0) return 0;
        int res = 0;
        while (num > 0) {
            if (num % 2 == 0) num /= 2;
            else num -= 1;
            res++;
        }
        return res;
    }
}
