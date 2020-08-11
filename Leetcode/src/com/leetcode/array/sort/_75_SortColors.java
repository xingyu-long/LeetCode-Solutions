package com.leetcode.array.sort;

public class _75_SortColors {

    /**
     * 75. Sort Colors
     * When: 2019/03/28
     *
     * solution:
     *
     * @param nums
     */

    //time: O(n) space:O(n/2) = O(n)
    public void sortColors(int[] nums) {
        //two-pass solution  来记录每一个颜色的个数
        int[] count = new int[]{0, 0, 0};
        for (int num: nums){
            if (num == 0) count[0]++;
            else if (num == 1) count[1]++;
            else count[2]++;
        }
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < count[0]; j++){
                nums[i] = 0;
                i++;
            }
            for (int j = 0; j < count[1]; j++){
                nums[i] = 1;
                i++;
            }
            for (int j = 0; j < count[2]; j++){
                nums[i] = 2;
                i++;
            }
        }
    }
    public void sortColors2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int pivot = 1;
        // two pass
        int smaller = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < pivot) exch(nums, smaller++, i);
        }
        // print(nums);
        
        int larger = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > pivot) exch(nums, larger--, i);
        }
        // print(nums);
    }
    
    public void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    

    /**
     * solution2: 利用l, r, index进行操作
     * 主要是left表示把0交换到左边
     * right表示把2交换到右边
     * 所以1 就是在中间
     */
    //time: O(n) space:O(1)
    public void sortColors3(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0;
        int right = nums.length - 1;
        int index = 0;
        //因为right本身就是数组的最后一个位置，能用的到。
        while (index <= right) {
            if (nums[index] == 0) {
                swap(nums, index++, left++);
            } else if (nums[index] == 1){
                index++;
            } else {
                swap(nums, index, right--);
            }
        }
    }
        public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
