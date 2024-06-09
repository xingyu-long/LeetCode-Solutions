package com.intern.MathWork;

public class SegregateEvenandOddnumbers {

    public static void seperate(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0;
        int right = nums.length - 1;
        int res = 0;
        while (left < right) {
            while (left < nums.length && nums[left] % 2 == 0) left++;
            while (right >= 0 && nums[right] % 2 != 0) right--;
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
                res++;
            }
        }
        System.out.println("time = " + res);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{12, 34, 45, 9, 8, 90, 3};
        seperate(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
