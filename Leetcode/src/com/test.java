package com;

import java.util.ArrayList;
import java.util.List;

public class test {

    // Deepest substring.
    public static List<String> solution1(String s) {
        int curr = 0;
        int max = 0;
        List<String> res = new ArrayList<>();
        int n = s.length();
        int index = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                curr++;
            } else if (ch == ')') {
                curr--;
            }
            max = Math.max(max, curr);
        }
        curr = 0;
        while (index < n) {
            char ch = s.charAt(index);
            if (ch == '(') {
                curr++;
                if (curr == max) {
                    // find the inside substring
                    int end = index + 1;
                    while  (end < n && s.charAt(end) != ')') {
                        end++;
                    }
                    res.add(s.substring(index + 1, end));
                    index = end - 1;
                }
            } else if (ch == ')'){
                curr--;
            }
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> res = solution1("a(bc(eed)(egfgdsg))f");
        System.out.println(res.toString());
    }
}
