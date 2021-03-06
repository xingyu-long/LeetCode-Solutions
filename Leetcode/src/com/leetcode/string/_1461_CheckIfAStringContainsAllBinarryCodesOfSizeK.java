/*
 * @Date: 03/12/2021 09:24:53
 * @LastEditTime: 03/12/2021 09:25:15
 * @Description: substring
 */
package com.leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class _1461_CheckIfAStringContainsAllBinarryCodesOfSizeK {
    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length() - k + 1; i++) {
            set.add(s.substring(i, i + k));
        }
        return set.size() == (int) Math.pow(2, k);
    }
}
