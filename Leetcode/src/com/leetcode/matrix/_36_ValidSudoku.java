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

    public boolean isValidSudoku(char[][] board) {
        // 使用三个HashSet来保持其是否有重复
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

                if (board[rowIndex + j / 3][colIndex + j % 3] != '.' && !cube.add(board[rowIndex + j / 3][colIndex + j % 3]))
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
}
