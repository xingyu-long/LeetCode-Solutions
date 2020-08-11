package com.leetcode.bfsANDdfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 05/25/2020
 * @Description: BFS
 **/
public class _1197_MinimumKnightMoves {
    // 考虑取正的情况，因为是对称的 所以是可以的，并且限定在大于[-1, -1]的边界里
    // 其实跟test case有关系吧
    // time:不好写
    public int minKnightMoves(int x, int y) {
        // BFS的问题，一旦遇到x，y即返回
        int[][] dirs = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        HashSet<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        x = Math.abs(x);
        y = Math.abs(y);
        queue.offer(new int[]{0, 0, 0});
        visited.add("0*0");
        // 如果在这里mark
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int dist = curr[2];
            int currX = curr[0], currY = curr[1];
            if (currX == x && currY == y) return dist;
            for (int[] dir : dirs) {
                int row = currX + dir[0], col = currY + dir[1];
                int nextDist = dist + 1;
                if (!visited.contains(row + "*" + col) && row >= -1 && col >= -1) {
                    queue.offer(new int[]{row, col, nextDist});
                    visited.add(row + "*" + col);
                }
            }
        }
        return -1;
    }
}
