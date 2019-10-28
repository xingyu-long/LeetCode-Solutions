package com.intern.Amazon;

import java.util.Arrays;
import java.util.HashMap;

public class MoviesonFlight {
    // two sum closet

    /**
     * Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
     * Output: [90, 125]
     * Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215
     * is the maximum number within 220 (250min - 30min)
     *
     * @param nums
     * @param target
     * @return
     */
    // ** 求index 容易test case过不了
    // 利用双指针
    //QUESTION: https://leetcode.com/discuss/interview-question/313719/Amazon-or-Online-Assessment-2019-or-Movies-on-Flight
    public static int[] findClosetByValue(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        Arrays.sort(nums);
        int max = 0;
        int one = -1;
        int two = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum <= target - 30) {
                if (sum > max) {
                    max = sum;
                    one = nums[left];
                    two = nums[right];
                }
                left++;
            } else {
                right--;
            }
        }
        return new int[]{one, two};
    }

    // 如果有重复的符合pair出现 也没关系，因为sorted


    public static void main(String[] args) {
        int[] nums = new int[]{90, 85, 75, 60, 120, 150, 125};
        int target = 250;
        int[] res = findClosetByValue(nums, target);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }

}
