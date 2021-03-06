package com.leetcode;

public class _1088_ConfusingNumberII {

    // DFS
    int count;
    public int confusingNumberII(int N) {
        count = 0;
        build(0, N);
        return count;
    }

    public void build(long num, int n) {
        if (num > n) return;
        if (num != 0) {
            if (rotate(num) != num) {
                System.out.println("num = " + num);
                count++;
            }
        }
        if (num != 0) build(num * 10, n); // 10的话会在num = 1触发到。
        build(num * 10 + 1, n);
        build(num * 10 + 6, n);
        build(num * 10 + 8, n);
        build(num * 10 + 9, n);
    }
    // 因为确定只有0,1,6,8,9这几个数字。
    public long rotate(long num) {
        long res = 0;
        while (num != 0) {
            int last = (int) num % 10;
            switch (last) {
                case 6:
                    last = 9;
                    break;
                case 9:
                    last = 6;
                    break;
            }
            res = res * 10 + last;
            num /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        _1088_ConfusingNumberII confusingNumberII = new _1088_ConfusingNumberII();
        System.out.println(confusingNumberII.confusingNumberII(20));
    }
}
