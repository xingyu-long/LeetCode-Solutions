package com.leetcode.string.TwoPointer;

import java.util.LinkedList;
import java.util.List;

public class _844_BackspaceStringCompare {
    // time:O(n) space:O(n)
    public boolean backspaceCompare(String S, String T) {
        String s1 = getRealStr(S);
        String s2 = getRealStr(T);
        return s1.equals(s2);
    }

    public String getRealStr(String str) {
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '#') list.add(str.charAt(i));
            if (str.charAt(i) == '#' && list.size() != 0) list.remove(list.size() - 1);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : list) {
            sb.append(ch);
        }
        return sb.toString();
    }

    // 利用two pointer。遇到#就计数，这样遇到其他字符就可以跳过。
    // 如果跳跃的步数为空然后碰到非#，需要比较，不相同则false。
    // 如果其中一个先走完也得要false
    // time:O(n) space:O(1)
    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int skipS = 0, skipT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else break;
            }

            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else break;
            }

            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) return false;
            if ((i >= 0) != (j >= 0)) return false;
            i--; j--;
        }
        return true;
    }
}
