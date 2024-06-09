package com.intern.Google.VO;

public class _152_ {
    // 利用四个变量取代，因为只需要max,prevMax, min, prevMin.
    public int maxProduct3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int min = nums[0];
        int prevMin = nums[0];
        int max = nums[0];
        int prevMax = nums[0];
        int res = nums[0];
        for (int  i = 1; i < n; i++) {
            max = Math.max(nums[i], Math.max(prevMin * nums[i], prevMax * nums[i]));
            min = Math.min(nums[i], Math.min(prevMin * nums[i], prevMax * nums[i]));
            res = Math.max(max, res);
            prevMax = max;
            prevMin = min;
        }
        return res;
    }
}
