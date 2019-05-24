package com.leetcode.math;

class _66_PlusOne {

     /**
     * 66. Plus One
     * When: 2019/04/03
     */
    public int[] plusOne(int[] digits) {
        /**
        * 考虑三种情况：
        * case 1: 1011 -> 1012
        * case 2: 1099 -> 1100
        * case 3: 9999 -> 10000 (这里需要新开辟空间，原有的长度+1即可)
        *
        */
        if (digits == null || digits.length == 0) return digits;
        
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        // 第三种情况
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}
