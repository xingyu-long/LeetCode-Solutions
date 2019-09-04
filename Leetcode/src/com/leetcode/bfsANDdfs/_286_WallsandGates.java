package com.leetcode.bfsANDdfs;

public class _286_WallsandGates {
    // 利用bfs吧  如何保持最小的情况？？ 只要是非-1就可以重新计算
    // 感觉bfs有些问题，如何保持更新最小？？？ (可以写移动后的数字小于当前的就可以更新！)
    // 使用dfs

    /**
     *  286. Walls and Gates
     *  When:2019/7/25
     *  Difficulty: Medium
     * @param rooms
     */
    //time:(m * n) space:(m * n)？？
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    public void dfs(int[][] rooms, int i, int j, int dis) {
        if (i < 0 || i >= rooms.length
                || j < 0 || j >= rooms[0].length || rooms[i][j] < dis) {
            return;
        }
        rooms[i][j] = dis;
        dfs(rooms,i - 1,j,dis + 1);
        dfs(rooms,i + 1,j,dis + 1);
        dfs(rooms,i,j - 1,dis + 1);
        dfs(rooms,i,j + 1,dis + 1);
    }

}