package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _239_SlidingWindowMaximum {

    /**
     * 239. Sliding Window Maximum
     * when: 2019/03/26
     *
     * 涉及到的数据结构：
     * deque
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {

        /**solution1：暴力循环
        if (nums.length < 1 || nums == null) return new int[0];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length - k + 1; i++)
        {
            int max = nums[i];
            for (int j = i; j < i + k; j++){
//                System.out.println("j = " + j);
                max = Math.max(max, nums[j]);
            }
            res.add(max);
        }
//        System.out.println(res.toString());
        int[] result = new int[res.size()];
        for (int i  = 0; i < res.size(); i++){
            result[i]=res.get(i);
        }
        return result;
        **/

        //solution2: deque Time: O(n) Space: O(n)
        // deque 存的是index 并且从大到小排列
        if (nums == null || nums.length == 0){
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++){

            // if window moves forward, the first element of deque has to be removed
            if (!deque.isEmpty() && deque.peekFirst() == i - k){
                deque.poll();
            }
            //这里具体的比较有点不太懂   // as long as nums[i] has value greater than deque, keep popping elements of deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.removeLast();
            }
            deque.offer(i);
            if ((i + 1) >= k){
                res[i + 1 - k] = nums[deque.peek()];
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        maxSlidingWindow(nums, k);
    }
}
