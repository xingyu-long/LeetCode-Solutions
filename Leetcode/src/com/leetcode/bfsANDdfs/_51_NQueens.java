package com.leetcode.bfsANDdfs;

import java.util.ArrayList;
import java.util.List;

public class _51_NQueens {

    /**
     * 51. N-Queens
     * When: 2019/06/03
     *
     * solution:
     * 利用backtracking（深度优先 DFS）
     * @param n
     * @return
     */

    // time: 不确定 space: O(n);
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        helper(n, 0, new ArrayList<Integer>(), res);
        return res;
    }

    // 回溯的主要函数 这里的colPlacements 每一个数表示当前行的第几个
    public void helper(int n, int row, List<Integer> colPlacements, List<List<String>> res) {
        // 表示已经完成，走到了最后一行
        if (row == n) {
            res.add(generateBoard(colPlacements, n));
        } else {
            for (int col = 0; col < n; col++) {
                colPlacements.add(col);
                if (isValid(colPlacements)) {
                    helper(n, row + 1, colPlacements, res);
                }
                colPlacements.remove(colPlacements.size() - 1);
            }
        }
    }

    // 验证是否有效
    // 如果是第一个数 这样进来的currentRow = 0，所以true
    public boolean isValid(List<Integer> colPlacements) {
        // 从第0行到当前行进行搜索是否满足
        int currentRow = colPlacements.size() - 1;
        for (int i = 0; i < currentRow; i++) {
            int diff = Math.abs(colPlacements.get(i) - colPlacements.get(currentRow));
            // 这里用来检测对角线是否有，如果位置的差值刚好等于两个行之差 则就是存在于对角线上
            if (diff == 0 || diff == Math.abs(i - currentRow)) {
                return false;
            }
        }
        return true;
    }

    // 生成结果
    public List<String> generateBoard(List<Integer> colPlacements, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (colPlacements.get(i) == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            board.add(sb.toString());
        }
        return board;
    }
}
