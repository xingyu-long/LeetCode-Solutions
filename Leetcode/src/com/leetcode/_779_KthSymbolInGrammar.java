package com.leetcode;

public class _779_KthSymbolInGrammar {
    /**
     *  779. K-th Symbol in Grammar
     *  When:2019/7/24
     *  Difficulty: Medium
     */
    //time: O(n) 因为只是一条路返回 经历n个点 space:O(n)
    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        if (K % 2 == 0) { // 是偶数的情况，这就是在右子树 如果父节点为0则返回1否则返回0
            return (kthGrammar(N - 1, K / 2) == 0) ? 1 : 0;
        } else {
            return (kthGrammar(N - 1, (K + 1) / 2) == 0) ? 0 : 1;
        }
    }
}
