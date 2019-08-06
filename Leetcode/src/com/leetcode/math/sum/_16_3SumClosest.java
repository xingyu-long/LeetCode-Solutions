package com.leetcode.math.sum;

import java.util.Arrays;

public class _16_3SumClosest {

    /**
     * 16. 3Sum Closest
     * When: 2019/04/10
     * Review1: 2019/8/6
     * solution: 跟普通的3 sum 类似。
     *
     * Test case:
     * nums = [-1, 2, 1, -4] target = 1
     * res = -1 + 2 + -4 = -3
     * after sort: -4, -1, 1, 2
     * -----i < 2-----
     * i = 0
     *      start = 1; end = 3;
     *      while (1 < 3)
     *          sum = -4 + -1 + 2 = -3
     *          start ++ -> 2
     *      while (2 < 3)
     *          sum = -4 + 1 + 2 = -1
     *          res = -1;
     *          start ++ -> 3
     *      弹出
     * i = 1
     *      start = 2; end = 3;
     *      while (2 < 3)
     *          sum = -1 + 1 + 2 = 2;
     *          end -- -> 2
     *          res = 2;
     *     弹出 因为start 不满足 < end
     *
     *
     * 这里减2  也可以表示出 start 和end 不同值不然就也不会运行while (start < end)这部分
     * time: O(n^2)
     * space: O(1)
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        // 类似于3 Sum的做法
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) {
                    end--;
                } else start++;
                // 用来比较，得到最小最接近于target的结果
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
            }
        }
        return res;
    }
}
