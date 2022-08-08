package com.leetcode.range_sum;

/**
 * @Date: 06/06/2020
 * @Description: prefix sum, math
 **/
public class _825_FriendsOfAppropriateAges {
    // time:O(n), space:O(1)
    // B: (0.5A + 7, A] 这样才能算有效的request
    public int numFriendRequests(int[] ages) {
        int res = 0;
        int[] numInAge = new int[121], sumInAge = new int[121];
        for (int age : ages) {
            numInAge[age]++;
        }
        for (int i = 1; i <= 120; i++) {
            sumInAge[i] = sumInAge[i - 1] + numInAge[i];
        }
        for (int i = 15; i <= 120; i++) {
            if (numInAge[i] == 0) continue;
            int count = sumInAge[i] - sumInAge[i / 2 + 7];
            res += count * numInAge[i] - numInAge[i]; // 减去自身的个数
        }
        return res;
    }
}
