package com.leetcode.random;

public class _470_ImplementRand10UsingRand7 {

    // time:O(1)
    // https://leetcode.com/problems/implement-rand10-using-rand7/discuss/150301/Three-line-Java-solution-the-idea-can-be-generalized-to-%22Implement-RandM()-Using-RandN()%22
    public int rand10() {
        // 不太会 rejection sampling
        int rand40 = 40;
        while (rand40 >= 40) {
            int row = rand7();
            int col = rand7();
            rand40 = (row - 1) * 7 + (col - 1); // [0, 39]
        }
        return rand40 % 10 + 1; // [1, 10]
    }

    // 防止报错
    public int rand7(){
        return 1;
    }
}
