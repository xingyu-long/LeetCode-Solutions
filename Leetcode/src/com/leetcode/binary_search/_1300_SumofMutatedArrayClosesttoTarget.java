package com.leetcode.binary_search;

public class _1300_SumofMutatedArrayClosesttoTarget {
    // 利用binary search，这个有点像那个cut题目类型。
    public int findBestValue(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        int left = 0;
        int right = target;
        int choose = 0;
        int diff = Integer.MAX_VALUE;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int sum = calSum(arr, mid);
            if (Math.abs(sum - target) < diff) {
                diff = Math.abs(sum - target);
                choose = mid;
            }
            if (sum >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (Math.abs(calSum(arr, left) - target) < diff) return left;
        if (Math.abs(calSum(arr, right) - target) < diff) return right;
        return choose;
    }

    public int calSum(int[] arr, int choose) {
        int sum = 0;
        for (int num : arr) {
            if (num > choose) sum += choose;
            else sum += num;
        }
        return sum;
    }
}
