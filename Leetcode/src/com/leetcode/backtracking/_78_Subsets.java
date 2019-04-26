package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _78_Subsets {

    /**
     * 78. Subsets
     * When: 2019/04/26
     *
     * solution: 回溯法
     * test case: [1, 2]
     * nums.length = 2
     * (1) res.add   # res = [[], ];
     * (2) i = 0;
     *        list.add -> list = [1];
     *        (2.1)helper(res, list, nums, 1)
     *              # res = [[], [1], ]
     *              i = 1
     *              list = [1,2]
     *              helper(res, list, nums, 2)  res.add # res = [[], [1], [1, 2]] 没有进入循环
     *              list.remove(1) list = [1]
     *        list.remove(list.size() - 1 = 0) list = [];
     * (3) i = 1;
     *      list.add -> list = [2];
     *      (3.1) helper(res, list, nums, 2) -> res.add # res = [[], [1], [1,2], [2]] 没有进入循环
     *            remove(list.size - 1= = 0) list = []
     *
     *  res = [[], [1], [1,2], [2]];
     *
     *  time : O(2 ^ n)
     *  space: O(n)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
