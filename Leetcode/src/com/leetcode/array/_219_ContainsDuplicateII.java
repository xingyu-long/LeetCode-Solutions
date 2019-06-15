package com.leetcode.array;

import java.util.HashMap;

public class _219_ContainsDuplicateII {

    /**
     *
     * 219. Contains Duplicate II
     * when: 2019/03/19
     *
     *
     * 思路的问题：
     * 当时误以为是要求出里面相同数字的最远的长度与k做比较，但其实只需要第一次碰见相同的元素就可以与k比较即可！
     * 题目是否存在这样两个值之差小于k
     * @param nums
     * @param k
     * @return
     */
    // time : O(n * n) space: O(n)
    public static boolean containsNearbyDuplicate(int[] nums, int k){
        if (nums.length <= 1) {
            return false;
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i]) && Math.abs(i - hashMap.get(nums[i])) <= k) {
                return true;
            }
            hashMap.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args){
        int [] nums = new int[]{1,2,3,1,2,3,1};
        int k = 2;
        System.out.println(containsNearbyDuplicate(nums, k));
    }
}
