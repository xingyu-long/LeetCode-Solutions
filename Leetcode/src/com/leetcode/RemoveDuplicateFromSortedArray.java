package com.leetcode;

import java.util.Arrays;

/**
 * Created by longxingyu on 2019/2/11.
 */

//LeetCode No.26
public class RemoveDuplicateFromSortedArray {

    public static int removeDuplicates(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int count = 1; //因为有序，所以至少第一个存在（即使 {1, 1, 1, 1}）
//        {1, 1, 2, 2, 3}
//        这里之前写的nums[count - 1]为啥可行？
        for (int i = 1; i < nums.length; i++){
            if(nums[i - 1] != nums[i]){
                nums[count++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return count;
    }

    public static void main(String[] args){
        System.out.println(removeDuplicates(new int[]{1,1,2,3,3,4,4,4,4,4,5}));
    }
}
