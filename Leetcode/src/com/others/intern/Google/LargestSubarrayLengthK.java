package com.intern.Google;

import java.util.ArrayList;
import java.util.List;

public class LargestSubarrayLengthK {

    public static Integer[] maxArray(Integer[] input, int k) {
        if (input == null || input.length == 0) return null;
        if (input.length <= k) return input;
        // 先保存到list里面去
        List<List<Integer>> arr = new ArrayList<>();
        for (int i = 0; i <= input.length - k; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                list.add(input[i + j]);
            }
            arr.add(list);
        }
//        for (List<Integer> list : arr) {
//            System.out.println(list.toString());
//        }
        Integer[] res = (Integer[]) arr.get(0).toArray();
        for (List<Integer> list : arr) {
            if (isGreater((Integer[]) list.toArray(), res)) {
                res = (Integer[]) list.toArray();
            }
        }
        return res;
    }


    public static int[] toArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

   public static boolean isGreater(Integer[] a, Integer[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) continue;
            else if (a[i] > b[i]) return true;
            else return false;
        }
        return false;
    }

    // unique integer
    public static Integer[] maxArray2(Integer[] input, int k) {
        if (input == null || input.length == 0) return null;
        if (input.length <= k) return input;
//        Integer[] a = new Integer[k];
         Integer[] res = new Integer[k];
        int maxIndex = 0;
        int maxVal = input[0];
        for (int i = 1; i <= input.length - k; i++) {
            if (input[i] > maxVal) {
                maxIndex = i;
                maxVal = input[i];
            }
        }
        int index = 0;
        for (int i = maxIndex; i < maxIndex + k; i++) {
            res[index++] = input[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] input = {5,3,2,76,75,73,23,74,86};
        int k = 3;
        Integer[] res = maxArray(input, k);
        for (Integer in : res) {
            System.out.print(in + " ");
        }

        Integer[] res2 = maxArray2(input, k);
        for (Integer in : res2) {
            System.out.print(in + " ");
        }
    }
}
