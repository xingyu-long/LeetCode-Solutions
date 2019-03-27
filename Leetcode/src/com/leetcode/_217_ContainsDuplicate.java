package com.leetcode;

import java.util.Arrays;

public class _217_ContainsDuplicate {

    /**
     * 217. Contains Duplicate
     * When: 2019/03/19
     *
     * 解法：hashmap的key-value；set无法重复的特性；sort 然后前后比较
     *
     * 涉及到的数据结构或者方法：
     * hashmap：get(value), containsKey(value), put(key, value)
     * hashset: add(value)
     * Arrays.sort(array)
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        /**
         * solution1: 利用key-value的形式计数
         if (nums == null || nums.length < 1) return false;

         HashMap<Integer, Integer> map = new  HashMap<>();
         for (int num: nums){
         if (!map.containsKey(num)){
         map.put(num, 1);
         } else {
         map.put(num, map.get(num) + 1);
         }

         if(map.get(num) > 1){
         return true;
         }
         }
         return false;
         **/

        /**
         *
         * solution2: 利用set不能放入重复的特性
         HashSet<Integer> set = new HashSet<>();
         for (int num: nums){
         if (! set.add(num)) return true;
         }
         return false;
         **/

        // 利用排序，然后前后两个找是否相同
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1]) return true;
        }
        return false;
    }

}
