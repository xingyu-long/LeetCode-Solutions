package com.leetcode.bfs_and_dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 06/16/2020
 * @Description: BFS,
 **/
public class _505_TheMazeII {

    // 可能会重复访问一个位置，因为这个是连续走，所以跟普通的bfs不同，需要这样更新。
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return -1;
        }
        return bfs(maze, start, destination);
    }

    // 因为这个是一直连续走，所以并不会是最短的情况，不像一步一步走的那种
    private int bfs(int[][] maze, int[] start, int[] end) {
        int m = maze.length, n = maze[0].length;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        dist[start[0]][start[1]] = 0;
        // count每次从dist开始数，这样才会更新最小
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int currX = curr[0];
                int currY = curr[1];
                int count = dist[curr[0]][curr[1]];
                while (isValid(maze, currX + dir[0], currY + dir[1])) {
                    currX += dir[0];
                    currY += dir[1];
                    count++;
                }
                // 如果count比之前的小，也需要加入到queue中
                // -1 表示未访问
                if (dist[currX][currY] == -1 || count < dist[currX][currY]) {
                    queue.offer(new int[]{currX, currY});
                    dist[currX][currY] = count;
                }
            }
        }
        return dist[end[0]][end[1]];
    }

    private boolean isValid(int[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            return false;
        }
        if (maze[x][y] != 0) {
            return false;
        }
        return true;
    }
}
