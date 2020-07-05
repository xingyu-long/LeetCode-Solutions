package com.leetcode.bfsANDdfs;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * @Date: 04/25/2020
 * @Description: BFS, Greedy
 **/
public class _1345_JumpGameIV {

    // 用BFS， DFS cache 会有些错误。
    // 然后那个next.clear() 记得要用。 这里还是不太懂
    // time:O(n) space:O(n)
    public int minJumps(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[] dp = new int[n];// 表示到i的最短路径，并且一开始赋值为-1可以作为visited 数组标记
        Arrays.fill(dp, -1);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], x -> new LinkedList<>()).add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        dp[0] = 0;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> next = map.get(arr[curr]);
            // 先放入进去，如果不行的话 后面也不会运算。
            next.add(curr - 1);
            next.add(curr + 1);
            for (int index : next) {
                // 等于-1表示没有访问过
                if (index >= 0 && index < n && dp[index] == -1) {
                    dp[index] = dp[curr] + 1;
                    queue.offer(index);
                }
            }
            // In the case where each index has the same value,
            // I only go to the neighbor (the same value) once
            // then I break all the edge by using: next.clear();
            map.get(arr[curr]).clear(); // 这里清空的是原来的map里面的吗？？？ 因为之前已经加入到queue了。
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        _1345_JumpGameIV jumpGameIV = new _1345_JumpGameIV();
        int[] nums = new int[]{7,7,7,7,7,7,7};
        jumpGameIV.minJumps(nums);
    }
}
