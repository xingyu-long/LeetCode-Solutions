package com.leetcode.array;

public class _1343_NumberofSubarraysofSizeKandAverageGreaterthanorEqualtoThreshold {
    // 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
    // time:O(n - k + 1 ~ n) space:O(1)
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        // prefix sum
        if (arr == null || arr.length == 0) return 0;
        int sum = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (i >= k - 1) {
                if (sum >= threshold * k) res++;
                sum -= arr[i - k + 1];
            }
        }
        return res;
    }

    // time:O(n) space:O(n)
    public int numOfSubarrays2(int[] arr, int k, int threshold) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }
        int index = 0;
        int res = 0;
        while (index < n && index + k <= n) {
            int sum = prefix[index + k] - prefix[index];
            if (sum >= k * threshold) res++;
            index++;
        }
        return res;
    }
}
