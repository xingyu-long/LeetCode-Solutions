package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class _937_ReorderDatainLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0)
            return null;
        Arrays.sort(logs, new logComparator());
        return logs;
    }

    public class logComparator implements Comparator<String> {
        @Override
        public int compare(String log1, String log2) {
            int idx1 = log1.indexOf(" ") + 1;
            int idx2 = log2.indexOf(" ") + 1;
            if (log1.charAt(idx1) >= 'a' && log1.charAt(idx1) <= 'z'
                    && log2.charAt(idx2) >= 'a' && log2.charAt(idx2) <= 'z') {
                String tail1 = log1.substring(idx1);
                String tail2 = log2.substring(idx2);
                // The letter-logs are sorted lexicographically by their contents
                int temp = tail1.compareTo(tail2);
                if (temp != 0) {
                    return temp;
                }
                else {
                    // If their contents are the same, then sort them lexicographically by their identifiers
                    return log1.compareTo(log2);
                }
            } else if (log1.charAt(idx1) >= 'a' && log1.charAt(idx1) <= 'z') {
                // The letter-logs come before all digit-logs
                return -1;
            } else if (log2.charAt(idx2) >= 'a' && log2.charAt(idx2) <= 'z') {
                // The letter-logs come before all digit-logs
                return 1;
            } else {
                return 0;
            }
        }
    }
}
