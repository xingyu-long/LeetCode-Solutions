package com.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class _954_ArrayofDoubledPairs {
    // 954. Array of Doubled Pairs
    // time:O(nlogn) space:O(n)
    public boolean canReorderDoubled(int[] A) {
        // 没有考虑到0的情况。
        if (A == null || A.length == 0) return true;
        int n = A.length;
        if (n % 2 != 0) return false;
        HashMap<Double, Integer> map = new HashMap<>();
        // 不能用hashset，这样会导致重复的数字没有被记录到。
        // 注意使用double 来保留位数。
        Arrays.sort(A); // sort之后再算就正确了？ 为啥
        for (int i = 0; i < n; i++) {
            if (map.containsKey(A[i] / 2.0)) {
                map.put(A[i] / 2.0, map.get(A[i] / 2.0) - 1);
                if (map.get(A[i] / 2.0) == 0) map.remove(A[i] / 2.0);
            } else if (map.containsKey(A[i] * 2.0)) {
                map.put(A[i] * 2.0, map.get(A[i] * 2.0) - 1);
                if (map.get(A[i] * 2.0) == 0) map.remove(A[i] * 2.0);
            } else {
                map.put(1.0 * A[i], map.getOrDefault(1.0 * A[i], 0) + 1);
            }
        }
        return map.size() == 0;
    }

    // 更巧妙的方法，依然利用排序后的（这里用TreeMap） 然后小于0的树肯定是找除以2的个数，大于0的找乘以2的个数、
    // 后面的相减不是很懂，
    public boolean canReorderDoubled2(int[] A) {
        if (A == null || A.length == 0) return true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            if (map.get(num) == 0) continue;
            int want = num < 0 ? num / 2 : num * 2;
            if (num < 0 && num % 2 != 0 || map.get(num) > map.getOrDefault(want, 0))
                return false;
            map.put(want, map.get(want) - map.get(num));
        }
        return true;
    }
}
