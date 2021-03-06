/*
 * @Date: 2020-03-30 14:29:52
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-30 14:33:27
 */
package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class _1389_CreateTargetArrayintheGivenOrder {
    // 就是利用list里面的insert即可
    // time:O(n^2) space:O(n)
    public int[] createTargetArray(int[] nums, int[] index) {
        if (nums == null || index == null) return new int[]{};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);    
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}