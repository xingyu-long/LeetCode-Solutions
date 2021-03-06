/*
 * @Date: 01/13/2021 10:25:27
 * @LastEditTime: 01/13/2021 10:26:30
 * @Description: Sort, Two pointer
 */
package com.leetcode.array.twoPointer;

import java.util.Arrays;

public class _881_BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }
        int n = people.length;
        Arrays.sort(people);
        int i, j;
        for (i = 0, j = n - 1; i <= j; j--) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
        }
        return n - j - 1;
    }
}
