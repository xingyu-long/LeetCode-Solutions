package com.leetcode.greedy;

import java.util.Arrays;

public class _948_Bag_of_Tokens {
    public int bagOfTokensScore(int[] tokens, int P) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        int res = 0, score = 0;
        Arrays.sort(tokens);
        int n = tokens.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            // 如果可以有足够的P，就先用
            if (P >= tokens[left]) {
                P -= tokens[left];
                score++;
                left++;
                res = Math.max(res, score);
            } else if (score > 0) { // 没有足够的P，减少score增加P；
                P += tokens[right];
                right--;
                score--;
            } else {
                break;
            }
        }
        return res;
    }
}
