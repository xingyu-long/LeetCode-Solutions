package com.leetcode.dynamic_programming;

public class _650_2KeysKeyboard {
    /**
     * 
     * @param n
     * @return
     */
    // backtracking 这个会TLE
    public int minSteps(int n) {
        return dfs(n, 0, 1, 0);
    }
    
    //需要考虑lastCopy
    public int dfs(int n, int step, int num, int lastCopy) {
        if (step > n) return Integer.MAX_VALUE;// 这个相当于最brute force
        if (num == n) {
            return step;
        }
        
        // copy all.
        int copy = dfs(n, step + 1, num, num);
        
        int paste = Integer.MAX_VALUE;
        // paste
        if (lastCopy != 0)
           paste = dfs(n, step + 1, num + lastCopy, lastCopy);
        
        int res = Math.min(copy, paste);
        return res;
    }
    

    /**
     * https://www.cnblogs.com/grandyang/p/7439616.html
     * https://www.youtube.com/watch?v=t-msCeBTlBY
     * N = 6; 所以是找因数
     * AA AA AA : AA + 3; k = 3
     * AAA AAA : AAA + 2; k = 2
     * A A A A A A : A + 6
     * @param n
     * @return
     */
    // 但其实是一个Math + DP的题。
    public int minSteps2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;// 代表i个A需要的次数。
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= i; j++) {
                if (i % j != 0) continue;
                int k = i / j;
                // + 1 copy, j - 1 copy. 
                dp[i] = Math.min(dp[i], dp[k] + 1 + j - 1);
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        _650_2KeysKeyboard keyboard = new _650_2KeysKeyboard();
        System.out.println(keyboard.minSteps2(3));
    }
}