package com.leetcode;

public class _12_IntegerToRoman {

    /**
     * 12. Integer to Roman
     * When: 2019/04/01
     *
     * solution:
     * 首先写出预设值（常见的值例如900, 400, 90...），然后一个for循环（这里是values.length）一直相减
     *
     * Test case:
     * num = 1965
     *  i = 0  while { num = 1965 - 1000 = 965 sb = "M"; }
     *  i = 1  while { num = 965 - 900 = 65 sb = "MCM"; }
     *  i = 2...5 没有进while循环
     *  i = 6  while { num = 65 - 50 = 15 sb = "MCML"}
     *  i = 7 没进while循环
     *  i = 8 while { num = 15 - 10 = 5 sb = "MCMLX"}
     *  i = 9 没进while
     *  i = 10 while {num = 10 - 10 = 0 sb = "MCMLXV"}
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        // 先写出预设的值
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
}
