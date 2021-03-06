package com.leetcode.array.sort;

public class _88_MergeSortedArray {

    /**
     * 88. Merge Sorted Array
     * When: 2019/03/28
     * review1:2019/8/9
     * review2: 2019/8/23
     * solution:
     * 从后往前比较，然后把大的放到nums1的后面（完成这样一次操作，index就需要--）
     * 也要考虑到nums2全都小于num1的情况 所以需要加while(j >= 0)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 从后往前相比较 大的数字就插到后面
        int i = m - 1; //记录nums1的最后不为空的位置
        int j = n - 1; //记录nums2的最后不为空的位置
        int k = m + n - 1; // 记录待插入的位置
        while (i >=0 && j >= 0){
            nums1[k--] = nums1[i] >= nums2[j] ? nums1[i--]: nums2[j--];
        }
        // 如果nums2全部都小于nums1 所以j需要继续填充到最前面
        while (j >= 0){
            nums1[k--] = nums2[j--];
        }
        // 因为如果nums2里面大些，肯定都会交换，并且nums1那些小的默认留下来了
    }

    // 典型的merge sort这里的total已经--了
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0) return;
        if (nums2 == null || nums2.length == 0) return;
        int total = m + n;
        total--;
        m--;
        n--;
        while (total >= 0 && m >= 0 && n >= 0) {
            nums1[total] = (nums2[n] >= nums1[m]) ? nums2[n--] : nums1[m--];
            total--;
        }
        while (n >= 0 && total >= 0) {
            nums1[total--] = nums2[n--];
        }
        while (m >= 0 && total >= 0) {
            nums1[total--] = nums1[m--];
        }
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1,2,3,0,0,0};
        int[] num2 = new int[]{2,5,6};
        int m = 3;
        int n = 3;
        merge2(num1, m, num2, n);
    }
}
