package com.leetcode.array.counter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 02/25/2020, 09/02/2020
 * @Description: Subarray, prefix sum
 **/

public class _1248_CountNumberofNiceSubarrays {
    // time:O(n) space:O(1)
    public int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public int atMost(int[] nums, int k) {
        if (k < 0)
            return 0;
        int res = 0;
        int start = 0, end = 0, count = 0, n = nums.length;
        while (end < n) {
            if (nums[end] % 2 != 0)
                count++;
            while (count > k) {
                if (nums[start] % 2 != 0)
                    count--;
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }

    // 将其转换为01 然后就成了930
    public int numberOfSubarrays2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += nums[i] % 2 == 0 ? 0 : 1;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
