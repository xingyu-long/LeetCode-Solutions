package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _78_Subsets {

    /**
     * 78. Subsets
     * When: 2019/04/26
     * Review1: 2019/7/15
     * review2:2019/10/7
     * <p>
     * solution: 回溯法
     * test case: [1, 2]
     * nums.length = 2
     * (1) res.add   # res = [[], ];
     * (2) i = 0;
     * list.add -> list = [1];
     * (2.1)helper(res, list, nums, 1)
     * # res = [[], [1], ]
     * i = 1
     * list = [1,2]
     * helper(res, list, nums, 2)  res.add # res = [[], [1], [1, 2]] 没有进入循环
     * list.remove(1) list = [1]
     * list.remove(list.size() - 1 = 0) list = [];
     * (3) i = 1;
     * list.add -> list = [2];
     * (3.1) helper(res, list, nums, 2) -> res.add # res = [[], [1], [1,2], [2]] 没有进入循环
     * remove(list.size - 1= = 0) list = []
     * <p>
     * res = [[], [1], [1,2], [2]];
     * <p>
     *
     * @param nums
     * @return
     */
    //time:O(2^n) space:O(n) 这里space的n表示最大的递归深度
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public static void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // BFS 主要是加入[] 然后每次加入一个节点。并且把以前的结果保存
    // [[]]
    // [[], [1]]
    // [[], [1], [2], [1, 2]]
    // time:O(2^n) space:O(n)
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        res.add(new ArrayList<>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(res.get(i));
                subset.add(num);
                res.add(new ArrayList<>(subset));
            }
        }
        return res;
    }


    // 可以用选择当前或者不选择，然后用index到达length之后结束。
    public void dfs2 (List<List<Integer>> res, List<Integer> cur, int[] nums, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        // 不选
        dfs2(res, cur, nums, index + 1);
        // 选
        cur.add(nums[index]);
        dfs2(res, cur, nums, index + 1);
        cur.remove(cur.size() - 1);
    }
}
