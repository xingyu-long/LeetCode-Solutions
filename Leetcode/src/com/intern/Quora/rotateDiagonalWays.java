package com.intern.Quora;

public class rotateDiagonalWays {

    // 这里目前还不懂
    public static void rotateDiagonal(int[][] matrix, int k) {
        int n = matrix.length;
        for(int s = 0; s < k; s++) {
            // rotate
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < i; j++) {
                    if(i != j && i + j != n - 1) {
                        int temp = matrix[i][j];
                        matrix[i][j] = matrix[j][i];
                        matrix[j][i] = temp;
                    }
                }
            }

            // flip
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n/2; j++) {
                    if(i != j && i + j != n - 1) {
                        int temp = matrix[i][j];
                        matrix[i][j] = matrix[i][n - 1 -j];
                        matrix[i][n - 1 -j] = temp;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        rotateDiagonal(matrix, 1);
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
