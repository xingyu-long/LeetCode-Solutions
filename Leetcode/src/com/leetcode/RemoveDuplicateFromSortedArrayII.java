package com.leetcode;

import java.util.Arrays;

/**
 * Created by longxingyu on 2019/2/12.
 */
public class RemoveDuplicateFromSortedArrayII {
    /**
     * LeetCode No. 189
     * 错误思路：1. 我以为需要一个flag进行标记个数 2. 之前的count没有搞 3. 这里使用i的话，会有覆盖作用，导致i=4的时候等于i=2 4. return错误 应该是count才对
     * 解题思路： 一定有两位保留，所以count=2（相当于从第三个开始）
     * case: [1, 1, 1, 2, 2, 3]
     *              2
     *                 c
     *                    i
     * result: [1, 1, 2, 2, 3, 3]
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        //判断nums是否满足
        if (nums.length <= 2) return nums.length;
        int count = 2;
        for (int i = 2; i < nums.length; i++){
            if(nums[count - 2] != nums[i]){
                nums[count++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return count;
    }

    public static void main(String[] args){
        removeDuplicates(new int[]{1, 1, 1, 2, 2, 3});
    }
}