package com.EPI.Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * palidromePartitioning
 */
public class PalidromePartitioning {

    public List<List<String>> partitioning(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), s, 0);
        return res;
    }

    public void helper(List<List<String>> res, List<String> cur, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String prefix = s.substring(start, i);
            if (isValid(prefix)) {
                cur.add(prefix);
                helper(res, cur, s, i);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public boolean isValid(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "0204451881";
        PalidromePartitioning pali = new PalidromePartitioning();
        List<List<String>> res = pali.partitioning(s);
        for (List<String> each : res) {
            for (String str : each) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}