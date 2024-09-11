/*
 * @Date: 07/31/2022 11:40:54
 * @LastEditTime: 07/31/2022 11:41:56
 * @Description: You need to fill out
 */
package com.leetcode.array;

public class _769_MaxChunksToMakeSorted {
    // 类似于找分割点，只有当前的最大值和index相同的时候，说明里面的数字
    // 可以形成一个chuck然后被排序
    // time: O(n) space: O(1)
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (i == max) {
                res++;
            }
        }
        return res;
    }
}
