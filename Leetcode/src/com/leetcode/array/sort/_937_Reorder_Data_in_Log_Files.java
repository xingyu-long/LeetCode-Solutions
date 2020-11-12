package com.leetcode.array.sort;

import java.util.Arrays;

/**
 * @Date: 10/24/2020
 * @Description: Customized Sort
 **/
public class _937_Reorder_Data_in_Log_Files {
    public String[] reorderLogFiles(String[] logs) {
        // letter-logs come before digit-logs;
        int n = logs.length;
        Arrays.sort(logs, (a, b) -> {
            int spaceIndexA = a.indexOf(' ');
            int spaceIndexB = b.indexOf(' ');
            char firstA = a.charAt(spaceIndexA + 1);
            char firstB = b.charAt(spaceIndexB + 1);
            if (firstA <= '9') {
                if (firstB <= '9') return 0; // no change;
                else return 1; // 把字符放在前面。
            }

            if (firstB <= '9') return -1; // 带有数字的在后面。
            int comp = a.substring(spaceIndexA + 1).compareTo(b.substring(spaceIndexB + 1));
            if (comp == 0) return a.substring(0, spaceIndexA).compareTo(b.substring(0, spaceIndexB));
            return comp;
        });

        return logs;
    }
}
