package com.leetcode.array.sub_array;

import java.util.HashMap;

/**
 * @Date: 05/10/2020
 * @Description: HashMap
 **/
public class _560_SubarraySumEqualsK {

    //  time:O(n * n) space:O(n)
    // preSum的做法，先计算出来，之后j倒着来，与i做差。
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] sum = new int[n];
        sum[0] = nums[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            if (sum[i] == k) {
                res++;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (sum[i] - sum[j] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    // time:O(n) space:O(n)
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 这个是记录，刚好sum为k的情况，1,2,3 -> sum: 1,3,6 k = 3.
        int res = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1); // 记录当前的个数
        }
        return res;
    }
}
