package com.intern.Quora;

public class GoodTuple {

    /**
     * GoodTuples
     * Give an array and find the count of a pair number and a
     * single number combination in a row of this array. Target array is
     * a[i - 1], a, a[i + 1]
     * Example:
     * Input: a = [1, 1, 2, 1, 5, 3, 2, 3]
     * Output: 3
     * Explain:
     * [1, 1, 2] -> two 1 and one 2(O)
     * [1, 2, 1] -> two 1 and one 2(O)
     * [2, 1, 5] -> one 2, one 1 and one five(X) [1, 5, 3] -> (X)
     * [5, 3, 2] -> (X)
     * [3, 2, 3] -> (O)
     * different characters
     * intput: a = "aabdcreff"
     * output: 5
     * 问a中存在多少a, a[i-1], a[i+1]都不同的情况
     */

    // 左右比较就可以了
    public static int goodTuple(int[] nums) {
        int res = 0;
        for (int i = 1; i + 1 < nums.length; i++) {
            res += checkForInt(nums[i - 1], nums[i], nums[i + 1]);
        }
        return res;
    }

    public static int checkForInt(int a, int b, int c) {
        if (a == b && a != c) return 1;
        else if (a == c && a != b) return 1;
        else if (b == c && b != a) return 1;
        else return 0;
    }

    public static int diffNum(String s) {
        int res = 0;
        for (int i = 1; i + 1 < s.length(); i++) {
            res += checkForCh(s.charAt(i - 1), s.charAt(i), s.charAt(i + 1));
        }
        return res;
    }

    public static int checkForCh(char a, char b, char c) {
        if (a != b && a != c && b != c) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 1, 5, 3, 2, 3};
        System.out.println(goodTuple(nums));
        String s = "aabdcreff";
        System.out.println(diffNum(s));
    }
}
