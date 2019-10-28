package com.intern.Amazon;

import java.util.HashMap;

public class FindPairWithGivenSum {

    /**
     * Given a list of positive integers nums and an int target, return indices of the
     * two numbers such that they add up to a "target - 30".
     * <p>
     * Conditions:
     * <p>
     * You will pick exactly 2 numbers.
     * You cannot pick the same element twice.
     * If you have multiple pairs, select the pair with the largest number.
     * <p>
     * Input: nums = [20, 50, 40, 25, 30, 10], target = 90
     * Output: [1, 5]
     * Explanation:
     * nums[0] + nums[2] = 20 + 40 = 60 = 90 - 30
     * nums[1] + nums[5] = 50 + 10 = 60 = 90 - 30
     * You should return the pair with the largest number.
     * @param nums
     * @param target
     * @return
     */
    public static int[] findPair(int[] nums, int target) {
        // 利用hashmap
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int first = -1;
        int second = -1;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();// value -> index
        for (int i = 0; i < nums.length; i++) {
            int left = target - 30 - nums[i];
            if (!map.containsKey(left)) {
                map.put(nums[i], i);
            } else {
                int temp = Math.max(nums[i], left);
                if (temp > max) {
                    first = i;
                    second = map.get(left);
                    max = temp;
                }
            }
        }
        return new int[]{first,second};
    }
    // 如果two sum 里面包含了duplicate？ 依然可行，因为每走到一个地方都计算剩下的情况。
    // eg: [3,3] target = 6 走到第一个的时候没有，走到第二个发现map里面有[3 -> 0] 所以返回[0,1]


    public static void main(String[] args) {
        int[] nums = new int[]{40, 5, 20, 55, 1, 7};
        int target = 90;
        int[] res = findPair(nums, target);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
