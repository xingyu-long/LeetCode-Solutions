package com.leetcode;

import java.util.HashMap;

public class _1056_ConfusingNumber {

    public boolean isConfusingNumber(int n) {
        if (n < 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(9, 6);
        map.put(8, 8);
        int rev = reverse(n);
        int confuse = 0;
        while (rev != 0) {
            if (map.get(rev % 10) == null) {
                return false;
            }
            confuse = confuse * 10 + map.get(rev % 10);
            rev /= 10;
        }
        return confuse != n;
    }

    public int reverse(int n) {
        int res = 0;
        while (n != 0) {
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }
}
