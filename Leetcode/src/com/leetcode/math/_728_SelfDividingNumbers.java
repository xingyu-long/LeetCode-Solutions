package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class _728_SelfDividingNumbers{
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (canDivided(i, i / 10,i % 10)) res.add(i);
        }
        return res;
    }

    public static boolean canDivided(int num, int forDigit, int digit) {
        if (digit == 0) return false;
        if (forDigit == 0 && num % digit == 0) return true;

        if (num % digit == 0 && canDivided(num, forDigit / 10, forDigit % 10)) {
            return true;
        }
        return false;
    }


    public List<Integer> selfDividingNumbers2(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (canDivided(i)) res.add(i);
        }
        return res;
    }

    public boolean canDivided(int num) {
        int temp = num;
        while (num != 0) {
            int digit = num % 10;
            if (digit == 0 || temp % digit != 0) return false;
            num /= 10;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(canDivided(128, 12, 8));
    }
}
