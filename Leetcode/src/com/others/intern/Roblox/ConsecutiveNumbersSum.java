package com.intern.Roblox;

public class ConsecutiveNumbersSum {

    public int consecutiveNumbersSum(int N) {
        if (N <= 0) return 0;
        int res = 0;
        for (int k = 1; ; k++) {
            int kx = N - k * (k - 1) / 2;
            if (kx <= 0) break;
            if (kx % k == 0) res++;
        }
        return res;
    }
}
