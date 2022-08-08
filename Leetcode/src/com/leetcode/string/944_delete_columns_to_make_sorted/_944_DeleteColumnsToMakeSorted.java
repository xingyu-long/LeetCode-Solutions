/*
 * @Date: 01/05/2021 09:01:07
 * @LastEditTime: 01/05/2021 09:03:15
 * @Description: String
 */
package com.leetcode.string;

public class _944_DeleteColumnsToMakeSorted {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int res = 0;
        int n = A[0].length();
        for (int i = 0; i < n; i++) {
            char prev = '0';
            for (String a : A) {
                char ch = a.charAt(i);
                if (ch != '0' && ch < prev) {
                    res++;
                    break;
                }
                prev = ch;
            }
        }
        return res;
    }
}
