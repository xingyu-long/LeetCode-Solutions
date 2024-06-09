package com.intern.Quora;

public class productMinusSum {

    //很简单，一个数字，求所有位数的乘积减去所有位数的和
    public static int product_sum(int num) {
        if (num == 0) return 0;
        int prod = 1;
        int sum = 0;
        while (num != 0) {
            int digit = num % 10;
            prod *= digit;
            sum += digit;
            num /= 10;
        }
        return prod - sum;
    }

    public static void main(String[] args) {
        System.out.println(product_sum(1234));
    }
}
