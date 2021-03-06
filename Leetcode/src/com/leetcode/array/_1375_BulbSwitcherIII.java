package com.leetcode.array;

public class _1375_BulbSwitcherIII {

    /**
     * When: 03/08/2020
     * @param light
     * @return
     */
    // time:O(n) space:O(n)
    public int numTimesAllBlue(int[] light) {
        // 查看连续的长度
        int n = light.length;
        boolean[] turn = new boolean[n];
        int maxTurnOn = 0, res = 0;
        for (int i = 0; i < n; i++) {
            turn[light[i] - 1] = true;
            // 看最长的sequence到那
            while (maxTurnOn < n && turn[maxTurnOn]) 
                maxTurnOn++;
            // 因为前面的++操作，所以代表i以及之前的已经都亮了
            if (maxTurnOn == i + 1) res++;
        }
        return res;
    }

    // time:O(n) space: O(1)
    public int numTimesAllBlue2(int[] light) {
        int n = light.length;
        int right = 0, res = 0;
        for (int i = 0; i < n; i++) {
            right = Math.max(right, light[i]);
            if (right == i + 1) res++;
        }
        return res;
    }
}