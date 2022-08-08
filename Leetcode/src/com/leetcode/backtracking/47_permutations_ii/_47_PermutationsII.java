package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _47_PermutationsII {

    /**
     * 47. Permutations II
     * When: 2019/04/30
     * Review1: 2019/7/16
     * review2:2019/10/13
     * review3: 11/10/2019
     * solution:
     * 这里的去重需要保留其used参数
     *
     * test case: [1, 1, 2]
     * 需要重新算
     *  i < 3
     * (1) i = 0
     *      used[0] = true
     *      list = [1]
     *      helper 进去    i < 3
     *              (1.1) i = 0; 已经used 所以continue
     *              (1.2) i = 1; used[1] = true
     *                          list = [1, 1]
     *                          helper i < 3
     *                               (1.2.1) i = 0 continue
     *                               (1.2.2) i = 1 continue
     *                               (1.2.3) i = 2
     *                                      used[2] = true
     *                                      list = [1, 1, 2]
     *                                      然后return 进去 res = [[1, 1, 2], ];
     *                                      used[2] = false;
     *                                      list = [1,1];
     *                          used[1] = false
     *                          list = [1]
     *              (1.3) i = 2;
     *                    used[2] = true
     *                    list = [1, 2]
     *                    helper 进去 i < 3
     *                              (1.3.1) i = 0 continue
     *                              (1.3.2) i = 1 used[1] = true list = [1,2,1] 然后return
     *                                              res = [[1, 1, 2], [1, 2, 1], ];
     *                              (1.3.3) i = 2 continue
     *                    used[2] = false;
     *                    list = [1]
     *      used[0] = false;
     *      list = []
     * (2) i = 1
     *      由于!used[i - 1] 以及nums[i] == nums[i - 1]这部分满足 所以就continue
     * (3) i = 2
     *      used[2] = true;
     *      list = [2]
     *      helper  i < 3
     *       (3.1) i = 0 used[0] = true; list = [2,1, ]
     *                      helper i < 3
     *                         (3.1.1) i = 0 continue
     *                         (3.1.2) i = 1 加入 list = [2,1,1] res = [[1, 1, 2], [1, 2, 1], [2,1,1]];
     *
     *                   used[0] = false;
     *                   list = [2]
     *       (3.2) i = 1
     *             continue
     *       (3.3) i = 2
     *             continue
     *      used[2] = false;
     *      list = []
     *
     * return [[1, 1, 2], [1, 2, 1], [2,1,1]]
     * @param nums
     * @return
     */
    // https://www.youtube.com/watch?v=43w8tXWKSLw
    // time: O(n!) space:O(n)
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return  res;
        Arrays.sort(nums);
        helper(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 用used来标记这个值是否被使用过
            // 表示有相同的元素并且前面那个用完了，现在这个就不用了 continue 跳走
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            list.add(nums[i]);
            helper(res, list, nums, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        permuteUnique(nums);
    }
}
