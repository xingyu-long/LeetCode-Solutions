package com.leetcode._43_multiply_strings;

/**
 * @Date: 07/16/2020
 * @Description: Math
 **/
public class _43_MultiplyStrings {

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "0";
        }
        int[] digits = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = product + digits[p2]; //这里加digits[p2] 因为个位数也需要运算的情况
                digits[p1] += sum / 10;
                digits[p2] = sum % 10;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int digit : digits) {
            if (!(digit == 0 && res.length() == 0)) {
                res.append(digit);
            }
        }
        return res.length() == 0 ? "0" : res.toString();
    }

    // 看起来更加清晰。
    public String multiply2(String num1, String num2) {
        if (num1 == null || num1.length() == 0 ||
            num2 == null || num2.length() == 0) {
            return "";
        }
        int[] digits = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int prod = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // position
                int carry = i + j; // 每次在前面一个
                int remain = i + j + 1;
                int sum = prod + digits[remain];
                digits[carry] += sum / 10;
                digits[remain] = sum % 10; // 这里不是加，因为前面已经算过sum了
            }
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (; index < digits.length; index++) {
            if (digits[index] != 0) {
                break;
            }
        }
        while (index < digits.length) {
            sb.append(digits[index++]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // 完全自己重做的。
    public String multiply3(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] arr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int index = i + j + 1;
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';
                int multi = n1 * n2;
                arr[index] += multi;
                arr[index - 1] += arr[index] / 10;// 需要进位
                arr[index] = arr[index] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num);
        }
        if (sb.charAt(0) == '0') {
            return sb.toString().substring(1);
        }
        return sb.toString();
    }
}
