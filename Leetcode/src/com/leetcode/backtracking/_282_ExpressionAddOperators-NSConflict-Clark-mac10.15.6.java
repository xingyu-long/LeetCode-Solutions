package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 06/11/2020
 * @Description: backtracking, calculator
 **/
public class _282_ExpressionAddOperators {

    // 思路主要参考grandyang
    // 其中val是指前面计算的值，cur表示当前的数，pre表示前一个数。
    // 当出现 * 的时候，(val-pre) + pre * cur
    // eg: 2 + 3 * 2 当运算到*这里的时候，val = 5 （前面的相加操作）所以为了得到2则就是5-3(pre)然后pre和cur相乘
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        // dfs(res, path, pos, val, pre)
        calculate(res, num, target, "", 0, 0, 0);
        return res;
    }

    public void calculate(List<String> res, String num, int target, String path, int pos, long val,
        long pre) {
        // iterate fully.
        if (pos == num.length()) {
            if (target == val) {
                res.add(path);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            // 需要注意！
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) { // 不能加入 * - + 操作，需要注意！
                calculate(res, num, target, path + cur, i + 1, cur, cur);
            } else {
                calculate(res, num, target, path + "+" + cur, i + 1, val + cur, cur);
                calculate(res, num, target, path + "-" + cur, i + 1, val - cur, -cur);
                // 遇到* 遇上先还原之前的情况。
                calculate(res, num, target, path + "*" + cur, i + 1, (val - pre) + pre * cur,
                    pre * cur);
            }
        }
    }
}
