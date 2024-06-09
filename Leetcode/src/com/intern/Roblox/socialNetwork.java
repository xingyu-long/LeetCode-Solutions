package com.intern.Roblox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class socialNetwork {
    public static void socialGraphs(List<Integer> counts) {
        // Write your code here
        // use sort Desc by group size and increase by ID
        int[][] arr = new int[counts.size()][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = i;
            arr[i][1] = counts.get(i);
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int diff = b[1] - a[1];
                if (diff == 0) return a[0] - b[0];
                else return diff;
            }
        });
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i][1];
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                list.add(arr[i][0]);
                if (num > 1 && j != num - 1) i++;
            }
            res.add(list);
        }
        for (List<Integer> each : res) {
            for (int i = 0; i < each.size(); i++) {
                System.out.print(each.get(i) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(2,1,1,2,1));
        socialGraphs(list);
    }
}
