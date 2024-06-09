package com.intern.Amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindKLengthSubstringsWithNoRepeatedCharacters {
    // *
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
                if (set.size() == K && temp.length() == K) res.add(temp);
            }
        }
        return res;
    }
    // 利用sliding window试试

    public static void main(String[] args) {
        List<String> res = subarraysWithKDistinct2("havefunonleetcode", 5);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }
}
