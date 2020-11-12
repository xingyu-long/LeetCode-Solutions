package com.new_grad.amazon;

import java.util.Arrays;

public class Ways_to_Split_String_Into_Prime_Numbers {

    public int countPrimeStrings(String inputString) {
        // word break一样
        return dfs(inputString, 0);
    }

    public int dfs(String str, int index) {
        if (index == str.length()) {
            return 1;
        }
        int res = 0;
        for (int i = 1; i <= str.length(); i++) {
            // if string[0, i-1] is prime.
            res += dfs(str.substring(i), i);
        }
        return res;
    }


    static int MOD= 1000000007;
    static boolean []isPrime = new boolean[1000000];

    // https://www.geeksforgeeks.org/count-of-ways-to-split-a-given-number-into-prime-segments/?ref=rp
    public static int countPrimeStrings2(String inputString) {
        build();
        int n = inputString.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return find(inputString, n, dp);
    }

    public static int find(String number, int i, int[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        int count = 0;
        for (int j = 1; j <= 6; j++) {
            if (i - j >= 0 && number.charAt(i - j) != '0' && isPrime(number.substring(i - j, i))) {
                count += find(number, i - j, dp);
                count %= MOD;
            }
        }
        dp[i] = count;
        return count;
    }

    public static boolean isPrime(String number) {
        int num = Integer.parseInt(number);
        return isPrime[num];
    }

    public static void build() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= 1000000; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < 1000000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(countPrimeStrings2("11373"));
    }
}
