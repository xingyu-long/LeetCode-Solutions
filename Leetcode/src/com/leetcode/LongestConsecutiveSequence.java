package com.leetcode;

import java.util.HashSet;

public class LongestConsecutiveSequence {

    /**
     * 128. Longest Consecutive Sequence
     * when: 2019/03/25
     *
     * solution:
     *
     * 涉及到的数据结构或者方法：
     * hashset
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        //赋值给HashSet
        for (int num: nums) set.add(num);
        for (int num: set){
                //表示没有存在 x-1 的数（后面表示一直是x+1, x+2 ....
            if (! set.contains(num - 1)){
                int currentNum = num;
                int currentRes = 1;

                //表示为持续+
                while (set.contains(currentNum + 1)){
                    currentNum += 1;
                    currentRes += 1;
                }
                res = Math.max(currentRes, res);
            }
        }
        return res;
    }

}