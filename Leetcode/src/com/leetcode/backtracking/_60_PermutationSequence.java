package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _60_PermutationSequence {

    /**
     * 60. Permutation Sequence
     * When: 2019/05/02
     *
     * solution:
     * 这里主要用的运算部分
     * int index = k / fact[i - 1];
     *  k = k % fact[i - 1];
     *  sb.append(res.get(index));
     *  res.remove(index);
     *
     *  然后这里的remove 如果发生在中间，后面的index就会变到前面
     *
     *  Test case:
     *  n = 3, k = 3;
     *  res = [1, 2, 3]
     *  fact = [1, 1, 2]
     *
     *  k = 2;
     *  (1) for 循环 i = 3
     *      index = k/fact[2] = 2/2 = 1
     *      k = k % fact[2] = 2%2 = 0
     *      sb = [res(1)] = [2]
     *      remove res.remove(index)
     *      res = [1, 3];
     *  (2) i = 2
     *      index = k / fact[1] = 0/1 = 0
     *      k = k % fact[1] = 0%1 = 0
     *      sb = [2, res[0]] = [2, 1];
     *      remove res.remove(index)
     *      res = [3]
     *  (3) i = 1
     *      index = k / fact[0] = 0/1 = 0
     *      k = k % fact[0] = 0%1 = 0
     *      sb = [2,1, res(0)] = [2, 1, 3]
     *      remove
     *      res = []
     * return "213"
     *
     * time: O(n^2)
     * space: O(n)
     * @param n
     * @param k
     * @return
     */
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

        k = k - 1;
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
        System.out.println(getPermutation(3,3));
    }
}
