package com.intern.Google.VO;

import java.util.HashSet;

public class _489_RobotRoomCleaner {

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0, new HashSet<>());
    }

    public void dfs(Robot robot, int x, int y, int curDir, HashSet<String> visited) {
        visited.add(x + "-" + y);
        robot.clean();// 每次走到先清扫
        for (int i = 0; i < 4; i++) { // 四个方向;
            int nextMove = (curDir + i) % 4;
            int newX = x + dirs[nextMove][0];
            int newY = y + dirs[nextMove][1];
            if (!visited.contains(newX + "-" + newY) && robot.move()) {
                dfs(robot, newX, newY, nextMove, visited);
                // 返回原来的位置(转180度)以及状态(move一下)。
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            // 每次的换方向。这样可以上下左右走。
            robot.turnRight();
        }
    }


    interface Robot {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        boolean move();

        // Robot will stay on the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();
        void turnRight();

        // Clean the current cell.
        void clean();
    }

}
