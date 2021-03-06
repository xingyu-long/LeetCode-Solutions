/*
 * @Date: 01/01/2021 14:37:29
 * @LastEditTime: 01/01/2021 14:37:59
 * @Description: Simulation
 */
package com.leetcode.string;

public class _657_RobotReturnToOrigin {
    // time: O(N)
    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 0) {
            return true;
        }

        int row = 0, col = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'U':
                    row--;
                    break;
                case 'D':
                    row++;
                    break;
                case 'L':
                    col--;
                    break;
                case 'R':
                    col++;
                    break;
            }
        }
        return row == 0 && col == 0;
    }
}
