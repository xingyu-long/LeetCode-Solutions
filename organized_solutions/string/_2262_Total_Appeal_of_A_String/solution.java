/*
 * @Date: 07/23/2022 18:37:02
 * @LastEditTime: 07/24/2022 11:15:56
 * @Description: String
 */
package com.leetcode.string;

import java.util.Arrays;

public class _2262_TotalAppealofAString {

    /*
     * 解题思路：
     * for example, s = "XXXa"
     * 对于第一个a来说，可以贡献到结果的潜在字符串有
     * XXXa, XXa, Xa, a
     * 如果考虑另外一个例子（有重复字符a的情况） s="XXXaXXaX"
     * 对于第二个a，左边可以到的地方则是第一个a，右边可以到的时候就是右边全部
     */
    // time: O(n)
    // space: O(1)
    public long appealSum(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] last = new int[26];
        Arrays.fill(last, -1);
        long res = 0, n = s.length();
        // 往左往右两边都找
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            res += (i - last[index]) * (n - i);
            last[index] = i;
        }
        return res;
    }
}
