/*
 * @Date: 12/06/2020 20:01:32
 * @LastEditTime: 12/06/2020 20:02:33
 * @Description: 
 */

package com.leetcode.array;

public class _390_EliminationGame {
    // Time: O(LogN) space: O(1)
    public int lastRemaining(int n) {
        boolean left = true;
        int remain = n;
        int res = 1;
        int step = 1;
        while (remain > 1) {
            if (left || remain % 2 == 1) {
                // will move head
                res += step;
            }
            remain /= 2;
            step *= 2;
            left = !left;
        }
        return res;
    }
}
