package com.leetcode.two_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18_4Sum {

    /**
     * 18. 4Sum
     * When: 2019/04/10
     * review1:2019/8/6
     *
     * solution:
     * 主要的思想与3 sum一致  只是需要多加一个j变量
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;
        Arrays.sort(nums);
        // 这里3是因为这里是4个数的和
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 这里的i > 0帮助了不用报出后面的异常
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int low = j + 1, high = nums.length - 1;
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (sum < target) {
                        low++;
                    } else high--;
                }
            }
        }
        return res;
    }
}
