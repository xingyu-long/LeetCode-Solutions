package com.leetcode.matrix;

import java.util.HashSet;

public class _36_ValidSudoku {

    /**
     * 36. Valid Sudoku
     * When: 2019/05/30
     *
     * solution:
     * (1) 利用三个HashSet
     * @param board
     * @return
     */

    /**
     *
        i = 0,   1,  2,  3,  4,  5,  6,  7,  8
 rowIndex   0    0   0   3   3   3   6   6   6
 ColIndex   0    3   6   0   3   6   0   3   6

        j = 0,   1,  2,  3,  4,  5,  6,  7,  8
   j / 3    0    0   0   1   1   1   2   2   2
   j % 3    0    1   2   0   1   2   0   1   2

     相当于每次检查j的时候，里面循环了9次 这样就结束了。
     */
    // https://www.youtube.com/watch?v=4-SF0-p98NM&list=PLvyIyKZVcfAk4vxVK-QQYha7VfE4SLm9q&index=36
    public boolean isValidSudoku(char[][] board) {
        // 使用三个HashSet来保持其是否有重复
        // 这个相当于每次检查的是一行，一列以及当前的小格子。
        for (int i = 0; i < board.length; i++) {
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> cols = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {
                // 检查每一行
                if (board[i][j] != '.' && !rows.add(board[i][j])) return false;
                if (board[j][i] != '.' && !cols.add(board[j][i])) return false;

                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);

                if (board[rowIndex + j / 3][colIndex + j % 3] != '.' &&
                        !cube.add(board[rowIndex + j / 3][colIndex + j % 3]))
                    return false;
            }
        }
        return true;
    }

    /**
     *
    这里不需要使用额外的space来存放信息
     i = 0,   1,  2,  3,  4,  5,  6,  7,  8
  row    0    0   0   3   3   3   6   6   6
  row <  3    3   3   6   6   6   9   9   9

     j = 0,   1,  2,  3,  4,  5,  6,  7,  8
  col    0    0   0   3   3   3   6   6   6
  col <  3    3   3   6   6   6   9   9   9

     对应着九个小部分
     */
    public boolean isValidSudoku2(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                if (!valid(board, i, j)) return false;
            }
        }
        return true;
    }

    public boolean valid(char[][] board, int i, int j) {
        // 检查列是否重复
        for (int row = 0; row < board.length; row++) {
            if (row == i) continue;
            if (board[row][j] == board[i][j]) return false;
        }
        // 检查行是否重复
        for (int col = 0; col < board[0].length; col++) {
            if (col == j) continue;
            if (board[i][col] == board[i][j]) return false;
        }
        // 检查3*3的小格
        for (int row = (i / 3) * 3; row < (i / 3 + 1) * 3; row++) {
            for (int col = (j / 3) * 3; col < (j / 3 + 1) * 3; col++) {
                if (row == i && col == j) continue;
                if (board[row][col] == board[i][j]) return false;
            }
        }
        return true;
    }

    // 跟上面第二个是等价的。
    public boolean isValidSudoku3(char[][] board) {
        // 3* 3 row:0-2 col 0-2, col 3-5, col 6-8
        // 3* 3 row:3-5 col 0-2, col 3-5, col 6-8
        // 3* 3 row:6-8 col 0-2, col 3-5, col 6-8
        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    if (!checkCol(board, i, j)) return false;
                    if (!checkRow(board, i, j)) return false;
                    if (!checkBox(board, i, j)) return false;
                }
            }
        }
        return true;
    }

    public boolean checkCol(char[][] board, int row, int col) {
        char ch = board[row][col];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == '.' || i == col) continue;
            if (board[row][i] == ch) return false;
        }
        return true;
    }

    public boolean checkRow(char[][] board, int row, int col) {
        char ch = board[row][col];
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == '.' || i == row) continue;
            if (board[i][col] == ch) return false;
        }
        return true;
    }

    public boolean checkBox(char[][] board, int row, int col) {
        int boxRow = row / 3;
        int boxCol = col / 3;
        char ch = board[row][col];
        for (int i = boxRow * 3; i <= boxRow * 3 + 2; i++) {
            for (int j = boxCol * 3; j <= boxCol * 3 + 2; j++) {
                if (i == row && j == col || board[i][j] == '.') continue;
                if (board[i][j] == ch) return false;
            }
        }
        return true;
    }
}
