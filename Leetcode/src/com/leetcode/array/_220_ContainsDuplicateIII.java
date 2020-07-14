package com.leetcode.array;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @Date: 2019/8/2, 07/13/2020
 * @Description: treeset, HashMap
 **/
public class _220_ContainsDuplicateIII {

    //  https://www.youtube.com/watch?v=yc4hCFzNNQc
    //  利用TreeSet，其实相当于维持一个BST，方便找它的floor和ceil值
    //  有点像保持一个sliding window在这里面的是符合k个的相差距离，主要比较的是在这里吗是否有相差<=t.
    // time:O(nlogK) space:O(k)
    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            //ceil >= 的最小值
            Integer ceil = set.ceiling(nums[i]);
            if (ceil != null && (Long.valueOf(ceil) - Long.valueOf(nums[i])) <= t) {
                return true;
            }

            //floor <= 最大值
            Integer floor = set.floor(nums[i]);
            if (floor != null && (Long.valueOf(nums[i]) - Long.valueOf(floor)) <= t) {
                return true;
            }

            set.add(nums[i]);
            if (i >= k) { // 维持k + 1的size
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    //  bucket做法：主要看是否存在一个bucket情况，或者相邻的情况，bucket大小定义为t+1（防止t = 0的情况）
    //  这里的(long) nums[i] - Integer.MIN_VALUE 是防止index为负的情况
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        HashMap<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long reMapperNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = reMapperNum / ((long) t + 1); // 这里用t，可以确定在同一个桶里。
            if (map.containsKey(bucket)
                || (map.containsKey(bucket - 1) && reMapperNum - map.get(bucket - 1) <= t)
                || (map.containsKey(bucket + 1) && map.get(bucket + 1) - reMapperNum <= t)) {
                return true;
            }
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, reMapperNum);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 9, 1, 5, 9};
        int k = 2;
        int t = 3;
        containsNearbyAlmostDuplicate2(nums, k, t);
    }
}
