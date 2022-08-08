/*
 * @Date: 2020-03-30 15:10:54
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-03-30 16:20:33
 */
package com.leetcode.bfs_and_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1391_CheckifThereisaValidPathinaGrid {

    // 当时写了大概，没有debug调通
    HashMap<Integer, Integer> dirs;

    public boolean hasValidPath(int[][] grid) {
        if (grid == null || grid[0] == null)
            return false;
        dirs = new HashMap<>();
        dirs.put(0, 1);
        dirs.put(1, 0);
        dirs.put(2, 3);
        dirs.put(3, 2);
        // up 0 down 1 left 2 right 3
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            map.put(i, new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                map.get(i).add(new ArrayList<Integer>());
            }
            if (i == 1) {
                map.get(i).get(3).addAll(Arrays.asList(1, 3, 5));
                map.get(i).get(2).addAll(Arrays.asList(1, 4, 6));
            } else if (i == 2) {
                map.get(i).get(0).addAll(Arrays.asList(2, 3, 4));
                map.get(i).get(1).addAll(Arrays.asList(2, 5, 6));
            } else if (i == 3) {
                map.get(i).get(2).addAll(Arrays.asList(4, 6, 1));
                map.get(i).get(1).addAll(Arrays.asList(5, 6, 2));
            } else if (i == 4) {
                map.get(i).get(1).addAll(Arrays.asList(5, 6, 2));
                map.get(i).get(3).addAll(Arrays.asList(1, 3, 5));
            } else if (i == 5) {
                map.get(i).get(0).addAll(Arrays.asList(2, 3, 4));
                map.get(i).get(2).addAll(Arrays.asList(1, 4, 6));
            } else {
                map.get(i).get(0).addAll(Arrays.asList(2, 3, 4));
                map.get(i).get(3).addAll(Arrays.asList(1, 3, 5));
            }
        }
        return dfs(grid, 0, 0, map, -1, -1);
    }

    public boolean dfs(int[][] grid, int i, int j, HashMap<Integer, List<List<Integer>>> map,
            int from, int prev) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] == 7)
            return false; // mark as 7;
        int curr = grid[i][j];
        if (dirs.get(from) != null) {
            int dir = dirs.get(from);
            if (!map.get(curr).get(dir).contains(prev))
                return false;
        }
        // 可以走通我再修改值
        grid[i][j] = 7;
        // 这个验证放在后面是因为会把前面都验证成功才行
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return true;
        boolean left = dfs(grid, i, j - 1, map, 2, curr);
        boolean right = dfs(grid, i, j + 1, map, 3, curr);
        boolean up = dfs(grid, i - 1, j, map, 0, curr);
        boolean down = dfs(grid, i + 1, j, map, 1, curr);
        grid[i][j] = curr;
        return left || right || up || down;
    }

    
    // search
    public boolean hasValidPath2(int[][] grid) {
        if (grid == null || grid[0] == null) return false;
        // up 0 down 1 left 2 right 3
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            map.put(i, new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                map.get(i).add(new ArrayList<Integer>());
            }
            if (i == 1) {
                map.get(i).get(3).addAll(Arrays.asList(1, 3, 5));
                map.get(i).get(2).addAll(Arrays.asList(1, 4, 6));
            } else if (i == 2) {
                map.get(i).get(0).addAll(Arrays.asList(2, 3, 4));
                map.get(i).get(1).addAll(Arrays.asList(2, 5, 6));
            } else if (i == 3) {
                map.get(i).get(2).addAll(Arrays.asList(4, 6, 1));
                map.get(i).get(1).addAll(Arrays.asList(5, 6, 2));
            } else if (i == 4) {
                map.get(i).get(1).addAll(Arrays.asList(5, 6, 2));
                map.get(i).get(3).addAll(Arrays.asList(1, 3, 5));
            } else if (i == 5) {
                map.get(i).get(0).addAll(Arrays.asList(2, 3, 4));
                map.get(i).get(2).addAll(Arrays.asList(1, 4, 6));
            } else {
                map.get(i).get(0).addAll(Arrays.asList(2, 3, 4));
                map.get(i).get(3).addAll(Arrays.asList(1, 3, 5));
            }
        }
        return bfs(grid, 0, 0, map);    
    }
    
    // BFS: 
    public boolean bfs(int[][] grid, int i, int j, HashMap<Integer, List<List<Integer>>> map) {
        // 第三位代表着往哪个方向走
        int[][] dirs = {{-1, 0, 0}, {0, -1, 2}, {0, 1, 3}, {1, 0, 1}};
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                int number = grid[x][y];
                if (x == grid.length - 1 && y == grid[0].length - 1) return true;
                for (int[] dir : dirs) {
                    int row = x + dir[0];
                    int col = y + dir[1];
                    int next = dir[2];
                    if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) continue;
                    if (visited[row][col]) continue;
                    if (!map.get(number).get(next).contains(grid[row][col])) continue;
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        _1391_CheckifThereisaValidPathinaGrid check = new _1391_CheckifThereisaValidPathinaGrid();
        int[][] grid = {{2, 4, 3}, {6, 5, 2}};
        System.out.println(check.hasValidPath2(grid));
    }
}
