package com.leetcode.array;

import com.leetcode.quickUnion.quickUnion;

import java.util.HashMap;
import java.util.HashSet;

public class _128_LongestConsecutiveSequence {

    /**
     * 128. Longest Consecutive Sequence
     * when: 2019/03/25
     * Review1: 2019/7/11
     * review2: 11/1/2019
     * 这里的时间复杂度为O(n)需要注意
     * 看每个元素最多访问两次，所以worst case: O(n + n)
     * https://zxi.mytechroad.com/blog/hashtable/leetcode-128-longest-consecutive-sequence/
     * <p>
     * hashset
     *
     * @param nums
     * @return
     */

    //time: O(n) space: O(n)
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        //赋值给HashSet
        for (int num : nums) set.add(num);
        for (int num : set) {
            //表示没有存在 x-1 的数（后面表示一直是x+1, x+2 ....
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentRes = 1;

                //表示为持续+
                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentRes += 1;
                }
                res = Math.max(currentRes, res);
            }
        }
        return res;
    }

    // 利用Union Find！
    public static int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 利用hashmap记录坐标值（这个用来后面连接使用）
        quickUnion uf = new quickUnion(nums.length);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) continue; // 移除 duplicate
            map.put(nums[i], i);
            if (map.containsKey(nums[i] + 1)) {
                uf.union(i, map.get(nums[i] + 1));
            }

            if (map.containsKey(nums[i] - 1)) {
                uf.union(i, map.get(nums[i] - 1));
            }
        }
        return uf.maxSize();
    }

}
