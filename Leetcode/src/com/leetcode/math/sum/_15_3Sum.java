package com.leetcode.math.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_3Sum {

    /**
     * 15. 3Sum
     * When: 2019/04/09
     * Review1: 2019/8/6
     * 主要排序然后使用two pointer的方法
     * time: O(n^2)
     * space: O(n)
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 用来可以使用two sum的two pointer方法（但是要注意去掉重复）
        for (int i = 0; i < nums.length - 2; i++) { //这里的nums.length - 2 是因为就算长度为3 那k只能取0 因为后面还有两个数
            if (i > 0 && nums[i] == nums[i - 1]) continue; //从第一个数才开始走这个
            // 这里的low 是除去nums[i]的第一个数
            int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];
            // 下面就是用two pointer的方法
            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < sum) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        for (List<Integer> a: threeSum(nums)){
            System.out.println(a.toString());
        }
    }
}