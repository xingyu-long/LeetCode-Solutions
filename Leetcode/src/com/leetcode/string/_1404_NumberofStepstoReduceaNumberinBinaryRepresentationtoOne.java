package com.leetcode.string;

public class _1404_NumberofStepstoReduceaNumberinBinaryRepresentationtoOne {
    // 其实原理和plus one那些一样
    public int numSteps(String s) {
        // add one
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        while (!s.equals("1")) {
            if (s.charAt(s.length() - 1) == '0') {
                s = s.substring(0, s.length() - 1);
            } else {
                // 1, 1, 1, 0, 1
                char[] chs = s.toCharArray();
                int n = chs.length;
                int carry = 0;
                for (int i = n - 1; i >= 0; i--) {
                    if (chs[i] == '0') {
                        carry = 0;
                        chs[i] = '1';
                        break;
                    } else {
                        carry = 1;
                        chs[i] = '0';
                    }
                }
                s = new String(chs);
                if (carry == 1) s = "1" + s;
            }
            res++;
        }
        return res;
    }
}
