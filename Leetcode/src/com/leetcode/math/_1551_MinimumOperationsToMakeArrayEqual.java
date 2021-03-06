/*
 * @Date: 04/06/2021 08:51:02
 * @LastEditTime: 04/06/2021 08:51:32
 * @Description: Math
 */
package com.leetcode.math;

public class _1551_MinimumOperationsToMakeArrayEqual {
    // 发现那个中间的数就是n，然后需要分别靠近这个数字。
    public int minOperations(int n) {
        // arr[i] = (2 * i) + 1
        // 1, 3, 5, 7, 9, 11 
        int res = 0;
        for (int i = 0; i < n / 2; i++) {
            int value = 2 * i + 1;
            res += n - value;
        }
        return res;
    }
}
