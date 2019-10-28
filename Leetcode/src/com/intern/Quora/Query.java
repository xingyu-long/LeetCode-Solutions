package com.intern.Quora;

public class Query {

    public static int query(int[] array, int[][] matrix) {
        if (array == null || array.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            int start = matrix[i][0];
            int end = matrix[i][1];
            int target = matrix[i][2];
            for (int j = start; j < end + 1; j++) {
                if (array[j] == target) res++;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,3,2};
        int[][] matrix = {{1,2,1}, {2,4,2}, {0,3,1}};
        System.out.println(query(arr, matrix));
    }
}
