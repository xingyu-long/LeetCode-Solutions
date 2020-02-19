package com.leetcode.string;

public class _551_StudentAttendanceRecordI {
    public boolean checkRecord(String s) {
        if (s == null || s.length() == 0) return true;
        int countA = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'A') {
                countA++;
                if (countA > 1) return false;
            } else if (s.charAt(i) == 'L') {
                int sum = 1;
                while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                    sum++;
                    i++;
                }
                if (sum > 2) return false;
            }
            i++;
        }
        return true;
    }
}
