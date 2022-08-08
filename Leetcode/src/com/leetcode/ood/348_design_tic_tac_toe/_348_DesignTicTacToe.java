package com.leetcode.ood;

/**
 * @Date: 10/26/2020
 * @Description: Design
 **/
public class _348_DesignTicTacToe {

    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;
    private int size;

    public _348_DesignTicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        this.size = n;
    }

    //time: O(1)
    public int move(int row, int col, int player) {
        int val = player == 1 ? 1 : -1;//相当于player1就+1 player2就-1 然后看是否绝对值达到3

        rows[row] += val;
        cols[col] += val;

        // Validate diagonal
        if (row == col) {
            diagonal += val;
        }

        if (col == (cols.length - row - 1)) {
            antiDiagonal += val;
        }

        // See who will win
        if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size
                || Math.abs(diagonal) == size || Math.abs(antiDiagonal) == size) {
            return player;
        }

        return 0;
    }
}
