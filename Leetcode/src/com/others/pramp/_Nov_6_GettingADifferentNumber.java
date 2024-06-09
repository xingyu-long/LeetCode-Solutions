package com.pramp;

public class _Nov_6_GettingADifferentNumber {
    // 其实这个跟finding missing positive一样的！！！
    static int getDifferentNumber(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        // your code goes here
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] < arr.length && arr[i] != i) {
                int index = arr[i];
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (i != arr[i]) return i;
        }
        return arr[arr.length - 1] + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,4,7,3};
        System.out.println(getDifferentNumber(arr));
    }
}
