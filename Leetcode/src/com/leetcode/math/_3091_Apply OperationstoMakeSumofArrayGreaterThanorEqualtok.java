class Solution {
    public int minOperations(int k) {
        if(k == 1)
            return 0;
        int[] dp = new int[k+1];
        return memo(k, 1, 1, dp);            
    }
    private int memo(int k, int sum, int max, int[] dp){
        if(k <= sum)
            return 0;
        if(dp[max] != 0)
            return dp[max];
        // +1
        int plus = memo(k, sum + 1, max + 1, dp) + 1;
        // duplicate
        int dupe = memo(k, sum + max, max, dp) + 1;
        
        dp[max] = Math.min(plus, dupe);
        return dp[max];
    }

    // https://leetcode.com/problems/apply-operations-to-make-sum-of-array-greater-than-or-equal-to-k/solutions/4916808/java-c-python-o-1-solution/
    // time: O(sqrt)
    public int minOperations2(int k) {
        int a = (int)(Math.sqrt(k));
        if (a * a == k) {
            return a + a - 2;
        } else if (a * (a + 1) >= k) {
            return a + a + 1 - 2;
        } else {
            // (a + 1) * (a + 1) >= k
            return a + a;
        }
    }
}