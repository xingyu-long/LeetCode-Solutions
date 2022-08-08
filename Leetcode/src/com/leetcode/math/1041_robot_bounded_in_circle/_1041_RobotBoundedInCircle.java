package com.leetcode.math;

/**
 * @Date: 07/19/2020
 * @Description: Math
 **/
public class _1041_RobotBoundedInCircle {
    // 是否在一个circle里面则需要看走完之后是否在原点或者是在其他的方向（通过多的几个轮回就会回到原点）
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, dir = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);
            if (ch == 'R') {
                dir = (dir + 1) % 4;
            } else if (ch == 'L') {
                dir = (dir + 3) % 4;
            } else {
                x += dirs[dir][0];
                y += dirs[dir][1];
            }
        }
        return x == 0 && y == 0 || dir > 0;
    }
}
