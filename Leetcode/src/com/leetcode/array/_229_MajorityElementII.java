package com.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _229_MajorityElementII {

    /**
     * 229. Majority Element II
     * When: 2019/03/18
     * Review: 2019/7/4
     *
     * 思路：
     * solution1： 可以使用之前相似的做法，然后用单独的ArrayList进行存储
     * solution2： 由于是 > 1/3*n次 可以使用Moore voting algorithm
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
         * time: O(n)
         * space: O(n)
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

    /** 先找身为大多数的number1 和 number 2 然后再验证*/
    //time:O(n) space:O(1)
    public List<Integer> majorityElement2(int[] nums) {
        //由于要大于 n / 3的数字 所以只有两个数可以存在。因为这样才能占 2/3
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        //先找出占多数的两个number
        int number1 = 0, number2 = 0;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            } else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        //计算是否大于n/3个
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(number1);
        }
        if (count2 > nums.length / 3) {
            res.add(number2);
        }
        return res;
    }
}
