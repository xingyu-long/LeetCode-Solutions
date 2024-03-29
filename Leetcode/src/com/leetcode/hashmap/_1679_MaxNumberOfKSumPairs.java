/*
 * @Date: 01/18/2021 09:33:13
 * @LastEditTime: 08/08/2022 16:30:50
 * @Description: HashMap
 */
package com.leetcode.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1679_MaxNumberOfKSumPairs {
    // time: O(NlogN)
    public int maxOperations(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int res = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                res++;
                left++;
                right--;
            } else if (sum > k) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    // time: O(N) 
    // 这种是边走边删除
    public int maxOperations2(int[] nums, int k) {
        // two sum follow up？
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(k - num)) {
                res++;
                map.put(k - num, map.get(k - num) - 1);
                if (map.get(k - num) == 0) {
                    map.remove(k - num);
                }
                map.put(num, map.getOrDefault(num, 0) - 1);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == 0) {
                map.remove(num);
            }
        }
        return res;
    }

    public int maxOperations3(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (int key : map.keySet()) {
            if (key * 2 == k) {
                res += map.get(key) / 2;
            } else if (map.containsKey(k - key)) {
                int min = Math.min(map.get(key), map.get(k - key));
                res += min;
                map.put(key, map.get(key) - min);
                map.put(k - key, map.get(k - key) - min);
            }
        }
        return res;
    }
}
