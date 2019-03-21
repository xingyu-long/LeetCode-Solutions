package com.leetcode;

import java.util.*;

public class TwoSum {
	// O(N * N)
	public int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		int len = nums.length;
		for (int i = 0; i < len; i++)
			for (int j = i + 1; j < len; j++)
				if(nums[i] + nums[j] == target){
					res[0] = i;
					res[1] = j;
					return res;
				}
		return null;			
	}
	
	//One-pass Hash Table (O(N))
	// 这里用值作为key 然后序号作为value，这样方便获取
	public int[] hashTwoSum(int[] nums, int target){
		Map<Integer, Integer> map = new Hashtable<>();
		for (int i = 0; i < nums.length; i++){
			int p = target - nums[i];
			if (map.containsKey(p)){
				return new int[] {map.get(p), i};
			}
			map.put(nums[i], i);
		}
		return null;
	}
	
	public static void main(String[] args){
		TwoSum solution = new TwoSum();
		int[] test = {2,7,11,15};
		int target = 26;
		int[] res = solution.hashTwoSum(test, target);
		for (int i = 0; i < res.length; i++){
			System.out.println(res[i]);
		}
	}
}