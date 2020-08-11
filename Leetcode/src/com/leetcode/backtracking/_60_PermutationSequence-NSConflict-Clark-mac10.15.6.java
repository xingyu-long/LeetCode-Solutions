package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/05/02, 2019/7/16, 2019/10/13, 06/07/2020
 * @Description: Design, Array of LinkedList
 **/
public class _60_PermutationSequence {

    //    首先是计算出来阶乘的情况，然后除以 i - 1 的阶乘就能知道在第一位、第二位等等的情况
    //time: O(n^2) space:O(n)
    public static String getPermutation(int n, int k) {
        List<Integer> res = new ArrayList<>(); // 记录原有的数据；当n = 3 则就是1,2,3
        for (int i = 1; i <= n; i++) {
            res.add(i);
        }

        int[] fact = new int[n]; //记录其阶乘的计算个数
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }

        k = k - 1; // 对应0开始。
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int index = k / fact[i - 1];
            k = k % fact[i - 1];
            sb.append(res.get(index)); // O(n)
            res.remove(index);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3));
    }
}
