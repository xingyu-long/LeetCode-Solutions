package com.leetcode.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _552_StudentAttendanceRecordII {
    // backtracking (TLE)
    // 552. Student Attendance Record II
    public int checkRecord(int n) {
        // backtracking.
        if (n < 0) return 0;
        List<String> res = new ArrayList<>();
        HashSet<Character> set = new HashSet<>();
        set.add('A');
        set.add('L');
        set.add('P');
        helper(res, "", n, set, 0);
        return res.size();
    }

    public void helper(List<String> res, String cur, int n, HashSet<Character> set, int index) {
        if (index > n) return;
        if (index == n) {
            res.add(cur);
            return;
        }
        for (char ch : set) {
            if (isExistA(cur) && ch == 'A') continue;
            if (index > 1 && ch == 'L' && cur.charAt(index - 1) == cur.charAt(index - 2) && cur.charAt(index - 2) == ch) continue;
            helper(res, cur + ch, n, set,index + 1);
        }

    }

    public boolean isExistA(String s) {
        for (char ch : s.toCharArray()) {
            if (ch == 'A') return true;
        }
        return false;
    }
}
