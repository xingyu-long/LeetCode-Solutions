package com.leetcode.binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Date: 2019/7/20, 2019/10/15, 05/13/2020
 * @Description: Set, Sort + Two Pointer.
 **/
public class _349_IntersectionofTwoArrays {

    // time:O(NlogM) space:O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0
            || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!list.contains(nums1[i]) && isExist(nums1[i], nums2)) {
                list.add(nums1[i]);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public boolean isExist(int target, int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target == nums[left] || target == nums[right]) {
            return true;
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

    // two pointer
    public int[] intersection3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, len1 = nums1.length;
        int j = 0, len2 = nums2.length;
        List<Integer> list = new ArrayList<>();
        while (i < len1 && j < len2) {
            // 先跳过那些重复的数字
            while (i + 1 < len1 && nums1[i] == nums1[i + 1]) {
                i++;
            }
            while (j + 1 < len2 && nums2[j] == nums2[j + 1]) {
                j++;
            }
            // if (i == len1 || j == len2) break;
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}
