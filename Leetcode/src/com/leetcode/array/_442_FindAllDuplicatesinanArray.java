package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 07/10/2020
 * @Solution: 利用1~N的条件，每次取出当前值-1作为index，访问之后就把这个赋值为相反值，
 * 如果发现是负数，则表明之前也碰到过这个，就用index + 1加入到结果集
 **/
public class _442_FindAllDuplicatesinanArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                res.add(index + 1);
            }
            nums[index] = -nums[index];
        }
        return res;
    }
    // 类似于41. find missing positive一样
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                exch(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        _442_FindAllDuplicatesinanArray find = new _442_FindAllDuplicatesinanArray();
        find.findDuplicates2(new int[]{2, 2, 3});
    }
}
