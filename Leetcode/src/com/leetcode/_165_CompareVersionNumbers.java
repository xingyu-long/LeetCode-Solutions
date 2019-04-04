package com.leetcode;

public class _165_CompareVersionNumbers {

    /**
     * 165. Compare Version Numbers
     * When: 2019/04/03
     *
     * solution:
     * 从前比到后，然后把中间无效的0去掉 如果还是没有比较出来，就用长度
     *
     * 寻找一下最优解
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int v1Length = v1.length;
        int v2Length = v2.length;
        int min = Math.min(v1.length, v2.length);
        for (int i = 0; i < Math.min(v1.length, v2.length); i++) {
            int a = Integer.parseInt(v1[i]);
            int b = Integer.parseInt(v2[i]);
            System.out.println("a = " + a + "b = "+b);
            if (a > b) return 1;
            else if (a < b) return -1;
        }

        for (int j = min; j < v1.length; j++) {
            if (Integer.parseInt(v1[j]) == 0) {
                v1Length--;
            }
        }
        for (int k = min; k < v2.length; k++) {
            if (Integer.parseInt(v2[k]) == 0) {
                v2Length--;
            }
        }

        if (v1Length > v2Length) return 1;
        else if (v1Length == v2Length) return 0;
        else return -1;
    }
    public static int compareVersion2(String v1, String v2) {
        int i1 = 0, i2 = 0, n1 = 0, n2 = 0;
        while (i1 < v1.length() || i2 < v2.length()) {
            while (i1 < v1.length() && v1.charAt(i1) != '.') {
                n1 = n1 * 10 + v1.charAt(i1++) - '0';
            }
            while (i2 < v2.length() && v2.charAt(i2) != '.') {
                n2 = n2 * 10 + v2.charAt(i2++) - '0';
            }
            if (n1 > n2) return 1;
            if (n1 < n2) return -1;
            n1 = n2 = 0;
            i1++; i2++;
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(compareVersion2("1.1", "1.0.1"));
    }
}
