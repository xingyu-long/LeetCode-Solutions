package com.leetcode.string.Subsequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _392_IsSubsequence {

    /**
     * 392. Is Subsequence
     * When:2019/8/9
     * Difficulty: Easy
     * @param s
     * @param t
     * @return
     */
    // 更加易读的方式。
    public boolean isSubsequence(String s, String t) {
        if ((s == null || s.length() == 0) && (t == null || t.length() == 0)) return true;
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == s.length();
    }
    // Follow up:
    // If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check 
    // one by one to see if T has its subsequence. In this scenario, how would you change your code?
    // 这个使用hashmap构建t的关系，然后对于s每次调用即可。 利用binary search进行搜索
    // time:O(~s)
    public boolean isSubsequence2(String s, String t) {
        if ((s == null || s.length() == 0) && (t == null || t.length() == 0)) return true;
        // use HashMap to maintain the relationship
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (!map.containsKey(t.charAt(i))) {
                map.put(ch, new ArrayList<>());
            }
            map.get(ch).add(i);
        }
        
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.get(ch) == null) return false; // 表示不存在该字符
            else {
                List<Integer> nums = map.get(ch);
                prev = find(nums, prev, 0, nums.size() - 1);
                if (prev == -1) return false;
                prev++;
            }
        }
        return true;
    }
    
    public int find(List<Integer> nums, int target, int left, int right) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums.get(left) >= target) return nums.get(left);
        else if (nums.get(right) >= target) return nums.get(right);
        else return -1;
    }
}
