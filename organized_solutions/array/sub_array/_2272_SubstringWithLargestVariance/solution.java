/*
 * @Date: 08/04/2022 10:23:43
 * @LastEditTime: 08/04/2022 10:43:09
 * @Description: Maximum subarray, DP
 */
package com.leetcode.array.sub_array;

public class _2272_SubstringWithLargestVariance {

    /*
     * 解题思路：
     * 遍历所有的小写字母字符（'a' -> 'z') 然后将其中一个变为1
     * 另外一个的值作为-1，其他不相关的就记为0。
     * aababbb -> [1, 1, -1, 1, -1, -1, -1]
     * 另外的话需要考虑的情况是
     * [-1, 0, 1, 1, 1, 1, -1, 0] 
     * 按照之前的解法可能就会找出[1, 1, 1, 1]为最大，但是这里的话就表示只有一种字符存在，即不符合
     * 题意，我们需要找出两个字符的个数之差。所以我们需要 一个从前往后的dp数组（和之前的一样）以及一个
     * 从后往前的dp数组，然后我们在-1的时候就停下记录值。
     */
    // time: O(n * 26 * 26)
    // space: O(n)
    public int largestVariance(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        int n = s.length();
        int res = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (count[i] == 0 || count[j] == 0) continue;
                if (i == j) continue;
                int[] nums = new int[n];
                for (int k = 0; k < n; k++) {
                    if (s.charAt(k) - 'a' == i) {
                        nums[k] = 1;
                    }
                    if (s.charAt(k) - 'a' == j) {
                        nums[k] = -1;
                    }
                }
                res = Math.max(res, helper(nums));
            }
        }
        return res;
    }
    
    public int helper(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        // 因为至少需要有一个元素
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int backwardSum = 0;
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            backwardSum = Math.max(backwardSum + nums[i], nums[i]);
            if (nums[i] == -1) {
                // 需要把重复的nums[i]减去
                res = Math.max(res, dp[i] + backwardSum - nums[i]);
            }
        }
        return res;
    }
}

