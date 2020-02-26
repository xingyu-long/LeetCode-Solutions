package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _248_StrobogrammaticNumberIII {
     /**
      * 248. Strobogrammatic Number III
      * @param low
      * @param high
      * @return
      */
      // 利用这种比较的形式比较好，并且不会有额外的工作
    public static int strobogrammaticInRange(String low, String high){
        int res = 0;
        List<String> list = new ArrayList<>();
        for (int i = low.length(); i <= high.length(); i++) {
            list.addAll(helper(i, i));
        }
        for (String num : list) {
            if ((num.length() == low.length() && num.compareTo(low) < 0) || (num.length() == high.length() && num.compareTo(high) > 0)) {
                continue;
            }
            res++;
        }
        return res;
    }

    private static List<String> helper(int cur, int max) {
        if (cur == 0) return new ArrayList<>(Arrays.asList(""));
        if (cur == 1) return new ArrayList<>(Arrays.asList("1", "8", "0"));

        List<String> res = new ArrayList<>();
        List<String> center = helper(cur - 2, max);

        for (int i = 0; i < center.size(); i++) {
            String tmp = center.get(i);
            if (cur != max) res.add("0" + tmp + "0");
            res.add("1" + tmp + "1");
            res.add("8" + tmp + "8");
            res.add("6" + tmp + "9");
            res.add("9" + tmp + "6");
        }
        return res;
    }

    public static void main(String[] args) {
        int res = strobogrammaticInRange("50", "100");
        System.out.print(res);
    }
}
