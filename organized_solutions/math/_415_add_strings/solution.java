/*
 * @Date: 05/11/2020 08:42:00
 * @LastEditTime: 08/09/2021 19:44:20
 * @Description: Add
 */
package com.leetcode.math;

public class _415_AddStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int m = num1.length() - 1, n = num2.length() - 1;
        int carry = 0;
        while (m >= 0 || n >= 0 || carry != 0) {
            if (m >= 0) {
                carry += num1.charAt(m) - '0';
                m--;
            }
            if (n >= 0) {
                carry += num2.charAt(n) - '0';
                n--;
            }
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }
}
