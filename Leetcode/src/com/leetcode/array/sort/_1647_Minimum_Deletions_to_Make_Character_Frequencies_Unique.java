/*
 * @Date: 11/17/2020 17:08:54
 * @LastEditTime: 11/17/2020 17:17:21
 * @Description: Sort, Greedy
 */

package com.leetcode.array.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Time: O(nlogn) n: number for each char.
public class _1647_Minimum_Deletions_to_Make_Character_Frequencies_Unique {
    public int minDeletions(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        int[] nums = new int[map.size()];
        int i = 0;
        for (int count : map.values()) {
            nums[i++] = count;
        }
        
        Arrays.sort(nums);
        int n = nums.length; 
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        for (int j = n - 1; j >= 0; j--) {
            while (nums[j] > 0 && !visited.add(nums[j])) {
                nums[j] -= 1;
                res += 1;
            }
        }
        return res;
    }

    // 可以使用bucket sort来优化排序的部分，但仅限于小写字母的情况。
    public int minDeletions_2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // Bucket sort;
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        for (int i = 25; i >= 0; i--) {
            while (count[i] > 0 && !visited.add(count[i])) {
                count[i]--;
                res++;
            }
        }
        return res;
    }
}
