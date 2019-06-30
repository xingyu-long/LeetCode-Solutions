package com.leetcode.array;
import java.util.Arrays;

public class _27_RemoveElement {

    /**
     *  27. Remove Element
     *  difficulty: Easy
        When: 2019/02/11
        review 1: 2019/06/30

        solution:
            利用two pointer： 一个从前往后扫描（i）一个记录结果（r）
            相当于当val等于nums[i]的时候res当前对应的数组值被替换并且向前移动一位
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val){
        //1.判断边界条件
        if(nums==null || nums.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != val){
                nums[res++] = nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args){
        _27_RemoveElement test = new _27_RemoveElement();
        int[] nums = {0, 1, 1, 3, 2, 1};
        int val = 1;
        System.out.print(test.removeElement(nums, val));
//        for (int i = 0; i < nums.length; i++){
//            System.out.println(nums[i]);
//        }
    }
}