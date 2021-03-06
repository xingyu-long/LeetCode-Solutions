package com.leetcode.string;

public class _1374_GenerateaStringWithCharactersThatHaveOddCounts {

    /**
     * When: 03/08/2020
     * @param n
     * @return
     */
    // time: O(n) space:O(n)
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append('a');
        }
        if (n % 2 == 0) {
            sb.append('b');
        } else {
            sb.append('a');
        }
        return sb.toString();
    }
}
