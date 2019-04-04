package com.leetcode;

public class _43_MultiplyStrings {
    /**
     * 43. Multiply Strings
     * When: 2019/04/04
     *
     * Test case: "123" * "45"
     * 主要的运算图如下
     *               1  2  3
     *                  4  5
     * ---------------------
     *                  1  5
     *               1  0
     *            0  5
     *               1  2
     *            0  8
     *         0  4
     * ----------------------
     * index   0  1  2  3  4
     *              <--[p1 p2]
     *              p1 = i + j
     *              p2 = i + j + 1
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "0";
        int[] digits = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >=0; i--)
            for (int j = num2.length() - 1; j >=0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = product + digits[p2]; //这里加digits[p2] 因为个位数也需要运算的情况
                digits[p1] += sum / 10;
                digits[p2] = sum % 10;
            }
        StringBuilder res = new StringBuilder();
        for (int digit: digits) {
            if (!(digit == 0 && res.length() == 0)) {
                res.append(digit);
            }
        }
        return res.length() == 0 ? "0": res.toString();
    }
}
