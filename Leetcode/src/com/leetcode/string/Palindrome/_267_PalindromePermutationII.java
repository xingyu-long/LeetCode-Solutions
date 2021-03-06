package com.leetcode.string.Palindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * _267_PalindromePermutationII
 */
public class _267_PalindromePermutationII {

    public static List<String> generatePalindromes(String s) {
        int odd = 0;
        String mid = "";
        List<String> res = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        // 先判断该字符是否可以组成有效的回文, 并且统计个数
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            odd += map.get(ch) % 2 != 0 ? 1 : -1;
        }
        if (odd > 1) return res;
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if (val % 2 != 0) mid += key;
            else {
                for (int i = 0; i < val / 2; i++) {
                    list.add(key);
                }
            }
        }
        helper(res, list, mid, new boolean[list.size()], new StringBuilder());
        return res;
    }

    public static void helper(List<String> res, List<Character> list, String mid, boolean[] visited, StringBuilder sb) {
        if(sb.length() == list.size()) {
            res.add(sb.toString() + mid + sb.reverse().toString());
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i) == list.get(i - 1) && !visited[i - 1]) continue;
            if (!visited[i]) {
                visited[i] = true;
                sb.append(list.get(i));
                helper(res, list, mid, visited, sb);
                visited[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String s = "abcba";
        List<String> res = generatePalindromes(s);
        for (String str : res) {
            System.out.println(str);
        }
    }
}