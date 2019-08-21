package com.leetcode.array.twoPointer;
import java.util.Arrays;

public class _26_RemoveDuplicateFromSortedArray {
    /**
     * 26. Remove Duplicate From Sorted Array
       When: 2019/2/11
       review 1: 2019/6/30
       review 2: 2019/8/20
       错误思路：算做nums[i]与nums[i+1]比较

     * 错误思路： 最开始认为不能用nums[count-1]
     * 思路解析：count的位置就是待插入的
     * case: [0, 1, 1, 2, 2]
     *       [0, 1, 2]
     *                 c
     *                    i
     * result: [0, 1, 2]
     */
    public static int removeDuplicates(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int count = 1; //因为有序，所以至少第一个存在（即使 {1, 1, 1, 1}）
//        {1, 1, 2, 2, 3}
        for (int i = 1; i < nums.length; i++){
            if(nums[i - 1] != nums[i]){
                nums[count++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return count;
    }

    public static void main(String[] args){
        System.out.println(removeDuplicates(new int[]{0, 1, 1, 2}));
    }
}
