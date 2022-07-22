/*
 * @Date: 07/19/2022 09:26:09
 * @LastEditTime: 07/19/2022 09:27:12
 * @Description: You need to fill out
 */
package com.leetcode.array.twoPointer;

public class _777_SwapAdjacentinLRString {
    // time: O(length of (start + end))
    // space: O(1)
    public boolean canTransform(String start, String end) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X')
                i++;
            while (j < n && end.charAt(j) == 'X')
                j++;
            // L和R是没办法互相跨越，所以这里如果不相同即返回false
            if (start.charAt(i) != end.charAt(j)) {
                return false;
            }
            // 当L在start字符串所在的坐标小于L在end字符串所在的坐标
            // L也无法向右移去match，所以false
            if (start.charAt(i) == 'L' && i < j) {
                return false;
            }
            // 同理
            if (start.charAt(i) == 'R' && i > j) {
                return false;
            }
            i++;
            j++;
        }
        while (i < n) {
            if (start.charAt(i) != 'X')
                return false;
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != 'X')
                return false;
            j++;
        }
        return true;
    }
}
