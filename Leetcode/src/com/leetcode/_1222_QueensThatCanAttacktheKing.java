package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class _1222_QueensThatCanAttacktheKing {

    /**
     * 1222. Queens That Can Attack the King
     * When:2019/10/13
     * Difficulty: Medium
     * solution；用八方向，每一次就内部初始化x，y然后按照一个方向使劲搜
     * 利用seen数组
     *
     * @param queens
     * @param king
     * @return
     */
    //time: O(N + 8 * 8; N < 64) space:O(8 * 8)
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<>();
        if (queens == null || queens.length == 0
                || queens[0] == null || queens[0].length == 0) return res;
        int[] directions = new int[]{1, 0, -1};
        boolean[][] seen = new boolean[8][8];
        for (int[] queen : queens) {
            seen[queen[0]][queen[1]] = true;
        }
        for (int dx : directions) {
            for (int dy : directions) {
                if (dx == 0 && dy == 0) continue;
                // 在一个方向一直找
                int x = king[0];
                int y = king[1];
                while (x + dx >= 0 && x + dx < 8 &&
                        y + dy >= 0 && y + dy < 8) {
                    x = x + dx;
                    y = y + dy;
                    if (seen[x][y]) {
                        res.add(Arrays.asList(x, y));
                        break;
                    }
                }
            }
        }
        return res;
    }

    // 显然用BFS 8 directions 先把坐标转为row * 8 + col 用HasetSet来装queens的位置，有利于速度。
    // 并且用visited[][] 记录位置，不需要backtracking。
    public List<List<Integer>> queensAttacktheKing2(int[][] queens, int[] king) {
        if (king == null || king.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int n = 8;
        for (int[] queen : queens) {
            set.add(queen[0] * n + queen[1]);
        }
        int[] dirs = {-1, 0, 1};
        for (int x : dirs) {
            for (int y : dirs) {
                // 在里面每次获取的原因是，这样不会影响其他。
                int curX = king[0];
                int curY = king[1];
                if (x == 0 && y == 0) continue; // skip yourself.
                while (curX + x >= 0 && curX + x < n
                        && curY + y >= 0 && curY + y < n) { // 一直在找八个方向的每一个。
                    curX += x;
                    curY += y;
                    if (set.contains(curX * n + curY)) {
                        res.add(new ArrayList<>(Arrays.asList(curX, curY)));
                        break;
                    }
                }
            }
        }
        return res;
    }
}
