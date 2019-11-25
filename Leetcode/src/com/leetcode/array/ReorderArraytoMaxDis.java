package com.leetcode.array;

import java.util.Arrays;

public class ReorderArraytoMaxDis {

    // sort + two pointer
    // https://www.geeksforgeeks.org/rearrange-array-maximum-minimum-form/
    public static int[] maxDis(int[] arr) {
        if (arr == null || arr.length == 0) return new int[]{};
        Arrays.sort(arr);
        int l = 0;
        int r = arr.length - 1;
        boolean flag = true;
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (flag)
                res[i] = arr[r--];
            else
                res[i] = arr[l++];
            flag = !flag;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,3,2,5,7,8,6,7,98,1};
        int[] res = maxDis(nums);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
