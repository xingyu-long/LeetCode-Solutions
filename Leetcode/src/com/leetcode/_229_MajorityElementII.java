package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _229_MajorityElementII {

    /**
     * 229. Majority Element II
     * When: 2019/03/18
     *
     * 思路：
     * solution1： 可以使用之前相似的做法，然后用单独的ArrayList进行存储
     * solution2： 由于是 > 1/3*n次 可以使用Moore voting algorithm 但是不懂
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        //判断边界条件
        if (nums == null || nums.length == 0){
            return new ArrayList<>();
        }
        /**
         *
         * solution 1：
         * 依然使用HashMap进行存储并且利用value存储，并且需要一个ArrayList来保存结果 (这里注意res里面可能会有重复的，所以需要排除这种情况)
         *
         **/
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList();

        for (int num: nums){
            if (!map.containsKey(num)){
                map.put(num, 1);
            } else{
                map.put(num, map.get(num) + 1);
            }

            if (map.get(num) > nums.length / 3 && !res.contains(num)){
                res.add(num);
            }
        }
        return res;
    }
}
