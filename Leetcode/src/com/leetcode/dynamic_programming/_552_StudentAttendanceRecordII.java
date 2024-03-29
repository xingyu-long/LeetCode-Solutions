package com.leetcode.dynamic_programming;

public class _552_StudentAttendanceRecordII {
    
    /**
     * When: 03/18/2020
     * @param n
     * @return
     */
    // time:O(n) space:O(n)
    public int checkRecord(int n) {
        int MOD = (int) Math.pow(10, 9) + 7;
        long[] PorL = new long[n + 1]; // 以P或者L结束，其中没有A的情况
        long[] P = new long[n + 1]; // 以P结束，其中没有A的情况
        PorL[0] = P[0] = 1;
        PorL[1] = 2;// i表示前i个数字能够构成情况
        P[1] = 1;
        for (int i = 2; i <= n; i++) {
            P[i] = PorL[i - 1];
            PorL[i] = (P[i] + P[i - 1] + P[i - 2]) % MOD;
        }
        
        long res = PorL[n];
        // 需要再放入一个a的情况即可
        for (int i = 0; i < n; i++) {
            long times = (PorL[i] * PorL[n - i - 1]) % MOD;
            res = (res + times) % MOD;
        }
        return (int) res;
    }
}
