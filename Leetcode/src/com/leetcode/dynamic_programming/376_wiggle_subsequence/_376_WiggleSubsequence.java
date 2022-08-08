/*
 * @Date: 12/02/2019 16:34:27
 * @LastEditTime: 03/18/2021 15:36:28
 * @Description: DP, 往回看
 */
package com.leetcode.dynamic_programming;

public class _376_WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        // 这个题就类似于求longest increasing subsequences.
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                } else {
                    up[i] = up[i - 1];
                    down[i] = down[i - 1];
                }
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    // time:O(n) space:O(n)
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) { // up
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) { // down
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[n - 1], up[n - 1]);
    }

    // time:O(n) space:O(1) 优化空间，因为只是依赖前后的值
    public int wiggleMaxLength3(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) { // up
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) { // down
                down = up + 1;
            }
        }
        return Math.max(down, up);
    }

    // 属于暴力解法。
    public int wiggleMaxLength4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] pos = new int[n], neg = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            pos[i] = 1;
            neg[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] - nums[j] > 0) {
                    pos[i] = Math.max(pos[i], neg[j] + 1); 
                } else if (nums[i] - nums[j] < 0) {
                    neg[i] = Math.max(neg[i], pos[j] + 1);
                }
            }
            res = Math.max(res, neg[i]);
            res = Math.max(res, pos[i]);
        }
        return res;
    }
}
