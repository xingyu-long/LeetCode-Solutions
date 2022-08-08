package com.leetcode.bfs_and_dfs;

/**
 * @Date: 05/25/2020
 * @Description: DFS, iterate
 **/
public class _419_BattleshipsinaBoard {

    // 因为说了只会给出有效的，所以不用担心无效的输入
    // 每次计算，只算入每个ship的左上角为有效，后面的都会因为上面或者左边存在而不用加入结果集
    // time:O(m * n) space:O(1)
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) { // 每次只记录可以新开一个ship的开始
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                res++;
            }
        }
        return res;
    }
}
