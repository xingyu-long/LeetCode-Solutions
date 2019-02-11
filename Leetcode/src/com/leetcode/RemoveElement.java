package com.leetcode;

import java.util.Arrays;

/**
 * Created by longxingyu on 2019/2/11.
 */

//LeetCode No.27
//思维误区：1. 以为需要动态修改原来数组的值，因为题目要求不能有额外的内存支出
//         2. 以为只是需要返回res的结果 数组没关系

// 利用two pointer： 一个从前往后扫描（i）一个记录结果（r）


public class RemoveElement {

    public int removeElement(int[] nums, int val){
        //1.判断边界条件
        if(nums==null || nums.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != val){
                nums[res++] = nums[i];
//                System.out.println(nums[i]);
//                System.out.println("length="+nums.length);
            }
        }
        System.out.println(Arrays.toString(nums));
        return res;
    }

    public static void main(String[] args){
        RemoveElement test = new RemoveElement();
        int[] nums = {2, 3, 3, 5, 5};
        int val = 3;
        System.out.print(test.removeElement(nums, val));
//        for (int i = 0; i < nums.length; i++){
//            System.out.println(nums[i]);
//        }
    }
}