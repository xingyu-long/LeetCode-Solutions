package com.pramp;

public class _Nov_11_DecodeVariations {
    // decode ways.
    static int decodeVariations(String S) {
        // your code goes here
        if (S == null || S.length() == 0) return 0;
        int n = S.length();
        int[] dp = new int[n + 1];
        // init dp[0] and dp[1]
        dp[0] = 1;
        dp[1] = (S.charAt(0) == '0') ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int oneDigit = S.charAt(i - 1) - '0';
            int twoDigits = (S.charAt(i - 2) - '0') * 10  + (S.charAt(i - 1) - '0');
            if (oneDigit > 0 && oneDigit < 10) {// one char
                dp[i] += dp[i - 1];
            }
            if (twoDigits >= 10 && twoDigits <= 26) { // two char
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

}
