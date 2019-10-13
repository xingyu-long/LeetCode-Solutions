package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1222_QueensThatCanAttacktheKing {

    /**
     * 1222. Queens That Can Attack the King
     * When:2019/10/13
     * Difficulty: Medium
     * solution；用八方向，每一次就内部初始化x，y然后按照一个方向使劲搜
     * 利用seen数组
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
}
