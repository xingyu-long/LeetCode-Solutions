package com.EPI.Recursion;

public class test {
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0, 9);
    }

    // how to backtrack? choose肯定是1-9 但是如何进行下一步呢？？
    // 返回的是 boolean。 需要好好理解。
    public void dfs(char[][] board, int row, int col, int end) {
        if (col == end) {
            row++;
            col = 0;
            if (row == end) return;
        }
        if (board[row][col] == '.') {
            for (char ch = '1'; ch <= '9'; ch++) {
                char temp = board[row][col];
                board[row][col] = ch;
                if (checkCol(board, row, col) && checkRow(board, row, col) && checkBox(board, row, col)) {
                    dfs(board, row, col + 1, end);
                }
                board[row][col] = temp; // restore;
            }
        } else {
            dfs(board, row, col + 1, end);
        }
    }

    public boolean checkCol(char[][] board, int row, int col) {
        char ch = board[row][col];
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == '.' || i == col) continue;
            if (board[row][i] == ch) return false;
        }
        return true;
    }

    public boolean checkRow(char[][] board, int row, int col) {
        char ch = board[row][col];
        for (int i = 0; i < board.length; i++) {
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


    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        test test = new test();
        test.solveSudoku(board);
    }
}
