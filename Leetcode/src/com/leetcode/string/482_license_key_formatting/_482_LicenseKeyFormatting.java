package com.leetcode.string;

public class _482_LicenseKeyFormatting {
    // time:O(n) space:O(n)
    public String licenseKeyFormatting(String S, int K) {
        String str = S.replaceAll("-", "").toUpperCase();
        int n = str.length();
        int remain = n % K;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            if (remain != 0) {
                sb.append(str.substring(i, i + remain) + ((i == n - remain) ? "" : "-"));
                i += remain;
                remain = 0;
            } else {
                sb.append(str.substring(i, i + K) + ((i == n - K) ? "" : "-"));
                i += K;
            }
        }
        return sb.toString();
    }

    //time:O(n) space:O(n) 倒着来，最后反转即可。
    public String licenseKeyFormatting2(String S, int K) {
        String str = S.toUpperCase().replace("-","");
        int n = str.length();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
            count++;
            if (count % K == 0 && i != 0) sb.append("-");
        }
        return sb.reverse().toString();
    }
}
