package com.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * _1363_LargestMultipleofThree
 */
public class _1363_LargestMultipleofThree {

    // 主要的想法就是看每个数字的和然后求余和每个数独立求余的关系！
    // time:O(nlogn) space:O(1)
    public String largestMultipleOfThree(int[] digits) {
        if (digits == null || digits.length == 0)
            return "";
        Arrays.sort(digits);
        List<Integer> remain1Index = new ArrayList<>(2); // 计算余数为1的digits的位置
        List<Integer> remain2Index = new ArrayList<>(2);
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] % 3 == 1 && remain1Index.size() < 2)
                remain1Index.add(i);
            else if (digits[i] % 3 == 2 && remain2Index.size() < 2)
                remain2Index.add(i);
        }
        int remainSum = Arrays.stream(digits).sum() % 3;
        // 并且这里一定保证了会有多个1和2，如果都只有一个的话
        // remainSum等于0，也不会触碰后面的if，然后返回全部
        if (remainSum == 1) { // 删除一个余数为1的情况或者是删除两个余数都为2的情况。
            if (remain1Index.size() >= 1)
                return getRes(digits, remain1Index.get(0), -1);
            else
                return getRes(digits, remain2Index.get(0), remain2Index.get(1));
        } else if (remainSum == 2) {
            if (remain2Index.size() >= 1)
                return getRes(digits, remain2Index.get(0), -1);
            else
                return getRes(digits, remain1Index.get(0), remain1Index.get(1));
        }
        return getRes(digits, -1, -1);
    }

    public String getRes(int[] digits, int ban1, int ban2) {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == ban1 || i == ban2)
                continue;
            else
                sb.append(digits[i]);
        }
        // for [0,0,0,0,0...,0,0];
        if (sb.length() > 0 && sb.charAt(0) == '0')
            return "0";
        return sb.toString();
    }

    // 还可以利用counting sort进行优化，原理和前面一样，记得保持个数
    public String largestMultipleOfThree2(int[] digits) {
        int[] count = new int[10];// 只有0-9这10种数字
        for (int i : digits)
            count[i]++;
        int remain1Count = count[1] + count[4] + count[7];
        int remain2Count = count[2] + count[5] + count[8];
        int remainSum = (1 * remain1Count + 2 * remain2Count) % 3;
        if (remainSum == 1) {
            if (remain1Count >= 1)
                remain1Count -= 1;
            else
                remain2Count -= 2;
        } else if (remainSum == 2) {
            if (remain2Count >= 1)
                remain2Count -= 1;
            else
                remain1Count -= 2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (i % 3 == 0) {
                while (count[i]-- > 0)
                    sb.append(i);
            } else if (i % 3 == 1) {
                // 这里记录的个数很重要！
                while (count[i]-- > 0 && remain1Count-- > 0)
                    sb.append(i);
            } else {
                while (count[i]-- > 0 && remain2Count-- > 0)
                    sb.append(i);
            }
        }
        if (sb.length() > 0 && sb.charAt(0) == '0')
            return "0";
        return sb.toString();
    }
}