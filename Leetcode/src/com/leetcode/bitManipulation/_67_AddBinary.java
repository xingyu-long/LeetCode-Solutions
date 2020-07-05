package com.leetcode.bitManipulation;

/**
 * @Date: 2019/04/04, 2019/7/24, 04/07/2020, 05/11/2020
 * @Description: Add,
 **/
public class _67_AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int remain = 0;
        while (i >= 0 || j >= 0 || remain != 0) {
            int sum = remain;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            sb.append(sum % 2);
            remain = sum / 2;
        }
        return sb.reverse().toString();
    }
}
