package com.leetcode.array.counter;

import java.util.Arrays;

public class _238_ProductofArrayExceptSelf {

    /**
     * 238. Product of Array Except Self
     * When:
     *
     * example:
     * [      1     |    a0   |  a0 * a1 | a0 * a1 * a2]
     * [a1 * a2 * a3| a2 * a3 |   a3     |      1 ]
     * @param nums
     * @return
     */

    // time:O(n) space: O(n)
    public static int[] productExceptSelf(int[] nums) {
        //solution1： 利用前后两个进行计算i
        int[] res = new int[nums.length];
        // 正向的计算
        int[] fwd = new int[nums.length];
        fwd[0]  = 1;
        // 反向的计算
        int[] bwd = new int[nums.length];
        bwd[nums.length - 1] = 1;

        // 正向赋值  [      1     |    a0   |  a0 * a1 | a0 * a1 * a2]
        for (int i = 1; i < fwd.length; i++){
            fwd[i] = fwd[i - 1] * nums[i - 1];
            System.out.println("fwd["+i+"] = "+fwd[i]);
        }

        // 反向赋值 [a1 * a2 * a3| a2 * a3 |   a3     |      1 ]
        for (int i = fwd.length - 2; i >= 0; i--){
            bwd[i] = bwd[i + 1] * nums[i + 1];
            System.out.println("bwd["+i+"] = "+bwd[i]);
        }

        // 前向 * 反向
        for (int i = 0; i < nums.length; i++){
            res[i] = fwd[i] * bwd[i];
        }
        return res;
    }

    //solution 2: 一样使用前后的方法，只是不用新开bwd数组
    public static int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        // 用来后面计算反向
        int right = 1;
        for (int i = 1; i < nums.length; i++){
            res[i] = res[i - 1] * nums[i - 1];
        }

        //反向计算 （这里是num.length - 1是因为需要完整赋值给那个数组。并且这里的i=0 right=a0*a1*a2*a3）
        //可是并不会乘入到结果中。
        for (int i = nums.length - 1; i >=0; i--){
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }
}
