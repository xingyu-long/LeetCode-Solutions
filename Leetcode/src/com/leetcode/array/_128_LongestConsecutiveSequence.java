package com.leetcode.array;

import com.leetcode.graph.UnionFind.UnionFind;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Date: 2019/03/25, 2019/7/11, 11/1/2019, 08/28/2020
 * @Description: Union Find, Set.
 **/
public class _128_LongestConsecutiveSequence {
    //time: O(n) space: O(n)
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
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
        UnionFind uf = new UnionFind(nums.length);
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
