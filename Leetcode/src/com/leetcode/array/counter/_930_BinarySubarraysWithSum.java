package com.leetcode.array.counter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 02/24/2020, 09/02/2020
 * @Description: Subarray, prefix sum
 **/
public class _930_BinarySubarraysWithSum {

    // time:O(n) space:O(1)
    public int numSubarraysWithSum(int[] A, int S) {
        if (A == null || A.length == 0)
            return 0;
        return atMost(A, S) - atMost(A, S - 1);
    }

    public int atMost(int[] nums, int target) {
        if (target < 0)
            return 0;
        int start = 0, end = 0, sum = 0, n = nums.length;
        int res = 0;
        while (end < n) {
            sum += nums[end];
            while (sum > target) {
                sum -= nums[start];
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }

    public int numSubarraysWithSum2(int[] A, int S) {
        if (A == null || A.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, res = 0;
        map.put(0, 1);
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (map.containsKey(sum - S)) {
                res += map.get(sum - S);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
