package com.leetcode.matrix;

public class _37_SudokuSolver {

    /**
     * 37. Sudoku Solver
     * When: 2019/05/30
     *
     * solution:
     * 如何填写位置？
     * 一个个遍历 出现 '.'的地方就使用1-9循环并且使用之前的验证，在这里用dfs
     * @param board
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // 这里跟之前的效果一样，因为依然每次走过一样的位置，前面那个刚好只是3 * 3的循环 这里是9的循环
    // row = 1, col = 3;
    // i = 0; [0][3]
    // i = 1; [0][4]
    // i = 2; [0][5] ....
    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false;
            if (board[row][i] == c) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
                    && board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
    // 这样写起来更加的elegant。
    private static boolean canPlaceValue(char[][] board, int row, int col, char charToPlace) {
        // Check column of the placement
        for (char[] placementRow: board) {
            if (charToPlace == placementRow[col]){
                return false;
            }
        }

        // Check row of the placement
        for (int i = 0; i < board[row].length; i++) {
            if (charToPlace == board[row][i]) {
                return false;
            }
        }

        // Check region constraints - get the size of the sub-box
        int regionSize = (int) Math.sqrt(board.length);

        int verticalBoxIndex = row / regionSize;
        int horizontalBoxIndex = col / regionSize;

        int topLeftOfSubBoxRow = regionSize * verticalBoxIndex;
        int topLeftOfSubBoxCol = regionSize * horizontalBoxIndex;

        for (int i = 0; i < regionSize; i++) {
            for (int j = 0; j < regionSize; j++) {
                if (charToPlace == board[topLeftOfSubBoxRow + i][topLeftOfSubBoxCol + j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // 这个写的比较易懂。
    public void solveSudoku2(char[][] board) {
        dfs(board, 0, 0);
    }

    // 利用返回值boolean，提前结束，否则就会被还原。
    public boolean dfs(char[][] board, int row, int col) {
        if (col == board[row].length) {
            row++;
            col = 0;
            if (row == board.length) return true;
        }
        if (board[row][col] == '.') {
            for (char ch = '1'; ch <= '9'; ch++) {
                if (isValid(board, row, col, ch)) {
                    board[row][col] = ch;
                    if (dfs(board, row, col + 1)) return true;
                    board[row][col] = '.';// restore.
                }
            }
        } else {
            return dfs(board, row, col + 1);
        }
        return false;
    }
}
