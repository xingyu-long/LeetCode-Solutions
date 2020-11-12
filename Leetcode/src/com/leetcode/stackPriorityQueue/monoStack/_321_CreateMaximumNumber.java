package com.leetcode.stackPriorityQueue.monoStack;

/**
 * @Date: 08/23/2020
 * @Description: Mono stack
 **/
public class _321_CreateMaximumNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[0];
        int n1 = nums1.length, n2 = nums2.length;
        // 尝试从nums1抽取k1个元素，以及nums2中抽取k2个元素。
        for (int k1 = 0; k1 <= k; k1++) {
            int k2 = k - k1;
            if (k1 > n1 || k2 > n2) continue;
            res = max(res, 0, maxNumber(maxNumber(nums1, k1), maxNumber(nums2, k2)), 0);
        }
        return res;
    }

    // 取出最大的k个数组成的数组。
    private int[] maxNumber(int[] nums, int k) {
        int[] res = new int[k];
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            while (j > 0 && nums[i] > res[j - 1] && nums.length - i > k - j) {
                // 上面的nums.length - i > k - j 还是不太懂。
                --j;
            }
            if (j < k)
                res[j++] = nums[i];
        }
        return res;
    }

    // 计算使用nums1和nums2一起构成最大的序列数组。
    public int[] maxNumber(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int s1 = 0, s2 = 0, index = 0;
        while (s1 != nums1.length || s2 != nums2.length) {
            res[index++] = max(nums1, s1, nums2, s2) == nums1 ? nums1[s1++] : nums2[s2++];
        }
        return res;
    }

    // 比较两个数组的大小。
    public int[] max(int[] nums1, int s1, int[] nums2, int s2) {
        for (int i = s1; i < nums1.length; i++) {
            int j = s2 + i - s1; // 比较每个相对的位置。
            if (j >= nums2.length) return nums1;
            if (nums1[i] < nums2[j]) return nums2;
            if (nums1[i] > nums2[j]) return nums1;
        }
        return nums2;
    }
}
