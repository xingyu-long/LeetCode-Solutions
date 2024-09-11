package com.leetcode.greedy;

/**
 * @Date: 2019/03/12, 2019/6/2, 2019/8/24, 08/25/2020
 * @Description: Greedy
 **/
public class _134_GasStation {

    // 解题思路：
    // 1. 首先通过累积和（总加油-总耗油）来计算是否存在这样的成立的情况
    // 2. 只要能够总的算下来 > 0 即可
    // 这个方法主要是表示只要在这个点之后的debt+remain都能做到 那就是从这个点出发即可
    //time: O(n) space:O(1)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0; //起始位置
        int remain = 0; //当前剩余燃料
        int debt = 0; // 前面没能走完的路上欠的债
        for (int i = 0; i < gas.length; i++) {
            remain += gas[i] - cost[i];
            if (remain < 0) {
                debt += remain;
                start = i + 1;
                remain = 0;
            }
        }
        return remain + debt >= 0 ? start : -1;
    }
}
