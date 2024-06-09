package com.intern.Quora;

public class FrameGenerator {

    public static void printFrame(int n) {
        if (n <= 0) return;
        for (int k = 0; k < n; k++) {
            System.out.print('*');
        }
        System.out.println();
        for (int i = 0; i < n - 2; i++) {
            System.out.print('*');
            // 打印第一行和最后一行
            for (int j = 1; j < n - 1; j++) {
                System.out.print(' ');
            }
            System.out.print('*');
            System.out.println();
        }
        for (int k = 0; k < n; k++) {
            System.out.print('*');
        }
    }
     public static void main(String[] args) {
        int[][] arr = new int[1000000][1000000];
        printFrame(1);
     }
}
