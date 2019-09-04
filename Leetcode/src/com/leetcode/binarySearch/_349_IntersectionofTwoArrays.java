package com.leetcode.binarySearch;

import java.util.Arrays;
import java.util.HashSet;

public class _349_IntersectionofTwoArrays {

    /**
     *  349. Intersection of Two Arrays
     *  When:2019/7/20
     *  Difficulty: Easy
     *
     * @param nums1
     * @param nums2
     * @return
     */

    // time:O(NlogN) space:O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2.length == 0 ||
                nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums2);//一会用nums2作为被搜索的数组
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int k = 0;
        int[] res = new int[set.size()];
        for (Integer num : set){
            res[k++] = num;
        }
        return res;
    }

    private boolean binarySearch(int[] nums, int num) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == num) return true;
            if (nums[mid] > num) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }

    // two set
    // time:O(m + n) space:O(n)
    public static int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> ret = new HashSet<>();
        for (Integer num : nums1) {
            set.add(num);
        }
        for (Integer num : nums2) {
            if (set.contains(num)) {
                ret.add(num);
            }
        }
        int k = 0;
        int[] res = new int[ret.size()];
        for (Integer num : ret) {
            res[k++] = num;
        }
        return res;
    }
}
