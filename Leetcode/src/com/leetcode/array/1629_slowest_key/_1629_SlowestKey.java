/*
 * @Date: 09/06/2021 14:58:00
 * @LastEditTime: 09/06/2021 14:58:01
 * @Description: Math, Logical
 */
package com.leetcode.array;

public class _1629_SlowestKey {
    // time: O(n)
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = 0;
        char res = '0';
        for (int i = 0; i < releaseTimes.length; i++) {
            if (i == 0) {
                max = releaseTimes[i];
                res = keysPressed.charAt(i);
            } else {
                int dur = releaseTimes[i] - releaseTimes[i - 1];
                if (dur > max) {
                    max = dur;
                    res = keysPressed.charAt(i);
                } else if (dur == max) {
                    if (keysPressed.charAt(i) > res) {
                        res = keysPressed.charAt(i);
                    }
                }
            }
        }
        return res;
    }
}
