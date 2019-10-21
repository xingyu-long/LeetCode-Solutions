package com.intern.Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SubstringswithexactlyKdistinctchars {

    public static int subarraysWithKDistinct(String s, int K) {
        // sliding window; at Most K的思路
        if (s == null || s.length() == 0) return 0;
        return atMostK(s, K) - atMostK(s, K - 1);
    }

    public static int atMostK(String s, int K) {
        int start = 0;
        int end = 0;
        int res = 0;
        HashMap<Character, Integer> count = new HashMap<>();
        while (end < s.length()) {
            if (count.getOrDefault(s.charAt(end), 0) == 0) K--;
            count.put(s.charAt(end), count.getOrDefault(s.charAt(end), 0) - 1); // 以前是--现在是加这个个数。
            while (K < 0) {
                count.put(s.charAt(start), count.get(s.charAt(start)) + 1);
                if (count.get(s.charAt(start)) == 0) K++;
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }
    // 利用set记录里面不同的个数以及拼接字符串
    // time:O(n^2) space:O(n)
    public static List<String> subarraysWithKDistinct2(String s, int K) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char firstCh = s.charAt(i);
            HashSet<Character> set = new HashSet<>();
            String temp = "" + firstCh;
            set.add(firstCh);
            for (int j = i + 1; j < s.length(); j++) {
                char ch = s.charAt(j);
                set.add(ch);
                temp += ch;
                if (set.size() == K && temp.length() >= K) res.add(temp);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        List<String> res = subarraysWithKDistinct2("pqpqs", 2);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }
}
