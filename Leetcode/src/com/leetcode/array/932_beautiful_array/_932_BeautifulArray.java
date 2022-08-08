/*
 * @Date: 01/16/2020 07:38:12
 * @LastEditTime: 07/28/2021 08:50:41
 * @Description: Array
 */
package com.leetcode.array;

import java.util.ArrayList;

public class _932_BeautifulArray {
    // 思路十分奇妙了
    // time:O(n)
    public int[] beautifulArray(int N) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        while (res.size() < N) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int num : res) {
                if (num * 2 - 1 <= N) temp.add(num * 2 - 1);
            }
            for (int num : res) {
                if (num * 2 <= N) temp.add(num * 2);
            }
            res = temp;
        }
        int[] ret = new int[res.size()];
        int i = 0;
        for (int num : res) {
            ret[i++] = num;
        }
        return ret;
    }
}
