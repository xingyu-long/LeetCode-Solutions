package com.leetcode.array.sub_array;

import java.util.HashMap;
import java.util.Map;

public class _974_Subarray_Sums_Divisible_by_K {
    // https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217985/JavaC%2B%2BPython-Prefix-Sum
    // 看第一个评论的证明
    public int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int n = A.length;
        int sum = 0;
        int res = 0;
        for (int num : A) {
            sum = (sum + num) % K;
            if (sum < 0) sum += K;

            if (map.containsKey(sum)) {
                res += map.get(sum);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
