package com.leetcode;

/**
 * Created by longxingyu on 2019/2/13.
 */

// LeetCode No. 41
public class _41_FirstMissingPositive {


    /**
     *
     * 利用"桶排序"思想
     * index + 1
     * 每个位置应该满足值等于该索引+1
     * 不满足就交换 （1. 输出第一个不符合该序列的index+1 2. 整个都是符合该序列 则输出nums.length + 1）
     *  index:[0, 1, 2, 3]
     * case: [ 3, 4, -1, 1]
     * i = 0 [-1, 4, 3, 1]
     * i = 1 [-1, 1, 3, 4]
     *  i = 2 不变
     *  i = 3[
     *  不懂：这里的-1位置不对（由于while 导致多循环了几次交换了位置），为啥要用while
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums){
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++){
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return nums.length + 1;
    }


    public static void main(String[] args){
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
    }
}
