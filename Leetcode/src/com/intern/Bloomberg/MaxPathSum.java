package com.intern.Bloomberg;

public class MaxPathSum {

    private static int max = 0;
    public static int maxPathSum(int[][] board) {
        if (board == null || board.length == 0 ||
        board[0] == null || board[0].length == 0) return 0;
        for (int i = 0; i < board[0].length; i++) {
            dfs(board,0, i, 0);
        }
        return max;
    }

    public static void dfs(int[][] board, int row, int col, int sum) {
        if (row < 0 || row >= board.length
                || col < 0 || col >= board[0].length) return;
        sum += board[row][col];
//        board[row][col] = Integer.MIN_VALUE;// visited
        if (row == board.length - 1) { // already to the bottom
            max = Math.max(max, sum);
        }
        dfs(board, row + 1, col - 1, sum);
        dfs(board, row + 1, col, sum);
        dfs(board, row + 1, col + 1, sum);
    }

    public static void main(String[] args) {
        int[][] board = {{1,2,3,4}, {5,6,7,8}, {9, 10, 11, 12}};
        System.out.println(maxPathSum(board));
    }

}
