package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _60_PermutationSequence {

    /**
     *  60. Permutation Sequence
     *  When: 2019/05/02
     *  Review1:2019/7/16
     *
     *  solution:
     *  https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
        首先是计算出来阶乘的情况，然后除以 i - 1 的阶乘就能知道在第一位、第二位等等的情况
        eg: n = 4, k = 14
        那么全排列的情况就是
        1 - {2, 3, 4}的全排列
        2 - {1, 3, 4}的全排列
        3 - {1, 2, 4}的全排列
        4 - {1, 2, 3}的全排列
        如果要确定第一位所选取的数字 那么就是 看它存在于 第1行、2行、3行、4行
        所以是除以 每一行有多少个元素，在这里面则就是 3!这么多个
        所以就是 13 / 3! = 2;
        然后还剩下了 1 那么继续
        就相当于是
        3 - 1 {2, 4}
        3 - 2 {1, 4}
        3 - 4 {1, 2}
        则就是除以 2 !
        然后继续

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
     * @param n
     * @param k
     * @return
     */

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
