/*
 * @Date: 12/12/2020 15:59:22
 * @LastEditTime: 12/12/2020 16:00:09
 * @Description: Top-down + memo + bit mask;
 */
package com.leetcode.dynamic_programming.game_theory;

public class _464_CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        int sum = ((1 + maxChoosableInteger) / 2 * maxChoosableInteger);
        if (sum < desiredTotal)
            return false;
        // 利用bit mask来标记位置
        int[] memo = new int[1 << maxChoosableInteger + 1]; // 1: win, -1: lose, 0: not calculated yet.
        return canWin(maxChoosableInteger, desiredTotal, memo, 0);
    }

    private boolean canWin(int maxChoosableInteger, int desiredTotal, int[] memo, int state) {
        if (desiredTotal <= 0) {
            return false;
        }
        if (memo[state] != 0)
            return memo[state] == 1;

        for (int i = 0; i < maxChoosableInteger; i++) {
            if ((state & (1 << i)) > 0)
                continue;// 表示已经访问过了
            if (!canWin(maxChoosableInteger, desiredTotal - (i + 1), memo, (state | 1 << i))) {
                memo[state] = 1;
                return true;
            }
        }
        memo[state] = -1;
        return false;
    }
}
