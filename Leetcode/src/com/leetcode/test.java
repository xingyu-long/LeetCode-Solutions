package com.leetcode;

/**
 * test
 */
public class test {
    public int numTimesAllBlue(int[] light) {
        if (light == null || light.length == 0) return 0;
        int n = light.length;
        boolean[] turn =  new boolean[n + 1];
        turn[0] = true;
        int res = 0, j;
        for (int i = 0; i < light.length; i++) {
            turn[light[i]] = true;
            for (j = light[i] - 1; j > 0; j--) if (!turn[j]) break;
            if (j == 0) res++;
        }
        return res;
    }

    public int numTimesAllBlue2(int[] light) {
        if (light == null || light.length == 0) return 0;
        int n = light.length;
        boolean[] turn = new boolean[n + 1];
        turn[0] = true;
        int res = 0;
        boolean allTurn = false;
        for (int i = 0; i < light.length; i++) {
            turn[light[i]] = true;
            for (int j = i - 1; j > 0; j--) {
                if (isTurnOnAll(turn, light[i]) && isTurnOnAll(turn, light[j])) {
                    allTurn = true;
                } else allTurn = false;
            }
            if (allTurn) res++;
        }
        return res;
    }
    
    public boolean isTurnOnAll(boolean[] turn, int i) {
        int j;
        for (j = i - 1; j > 0; j--) if (!turn[j]) break;
        if (j == 0) return true;
        return false;
    }
    public static void main(String[] args) {
        test test = new test();
        int[] light = {2,1,3,5,4};
        test.numTimesAllBlue2(light);
    }
}