package com.leetcode.math;

public class _258_AddDigits {


    /**
     *  258. Add Digits
     *  When: 2019/7/18
     *  Difficulty: Easy
     * @param num
     * @return
     */

    //time: O(?) space:O(1)
    public int addDigits(int num) {
        if (num < 10) return num;
        String res = Integer.toString(num);
        while (res.length() != 1) {
            int sum = 0;
            for (int i = 0; i < res.length(); i++) {
                sum += res.charAt(i) - '0';
            }
            res = Integer.toString(sum);
        }
        return Integer.parseInt(res);
    }
    // time:O(logn) space:O(1)
    // num = 2345
    // sum = 5, num = 234
    // sum = 9, num = 23
    // sum = 12, num = 2
    // sum = 14, num = 0;
    // 进入 return 5;
    public int addDigits2(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        if (sum >= 10) {
            return addDigits2(sum);
        } else {
            return sum;
        }
    }

    // 奇淫技巧了
    // https://leetcode.com/problems/add-digits/discuss/68580/Accepted-C%2B%2B-O(1)-time-O(1)-space-1-Line-Solution-with-Detail-Explanations
    public static int addDigits3(int num) {
        return (num - 1) % 9 + 1;
    }

}
