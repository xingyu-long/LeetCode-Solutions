package com.leetcode.array;

import java.util.HashMap;

public class _560_SubarraySumEqualsK {
    /**
     * 这里的连续和可以由一个数字构成，
     *
     * @param nums
     * @param k
     * @return
     */
    // time:O(n*n) space:O(1)
    // 表示 第i轮，然后从i开始算累计和看是否有符合的。
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }

    //  time:O(n * n) space:O(n)
    // preSum的做法，先计算出来，之后j倒着来，与i做差。
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] sum = new int[n];
        sum[0] = nums[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            if (sum[i] == k) res++;
            for (int j = i - 1; j >= 0; j--) {
                if (sum[i] - sum[j] == k) res++;
            }
        }
        return res;
    }

    // time:O(n) space:O(n)
    public int subarraySum3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 这个是记录，刚好sum为k的情况，1,2,3 -> sum: 1,3,6 k = 3.
        int res = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
