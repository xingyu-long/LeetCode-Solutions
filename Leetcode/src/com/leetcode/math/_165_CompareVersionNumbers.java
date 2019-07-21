package com.leetcode.math;

public class _165_CompareVersionNumbers {

    /**
     *  165. Compare Version Numbers
     *  When: 2019/04/03
     *  Review1: 2019/7/21
     * solution:
     * 从前比到后，然后把中间无效的0去掉 如果还是没有比较出来，就用长度
     *
     * 寻找一下最优解
     * @param version1
     * @param version2
     * @return
     */
    // time: O(max(m,n)) space: O(n)
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

    // 计算每次两个点之内的比较
    // time:O(max(m,n)) space:O(1)
    public static int compareVersion2(String version1, String version2) {
        int i1 = 0, i2 = 0, n1 = 0, n2 = 0;
        while (i1 < version1.length() || i2 < version2.length()) {
            while (i1 < version1.length() && version1.charAt(i1) != '.') {
                n1 = n1 * 10 + version1.charAt(i1++) - '0';
            }
            while (i2 < version2.length() && version2.charAt(i2) != '.') {
                n2 = n2 * 10 + version2.charAt(i2++) - '0';
            }
            if (n1 > n2) return 1;
            if (n1 < n2) return -1;
            n1 = n2 = 0;
            i1++; i2++;
        }
        return 0;
    }

    public int compareVersion3(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < Math.max(v1.length, v2.length); i++){
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            } //这里不设置等于的情况，因为中间可能会相同
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion2("1.101", "1.0.1"));
    }
}
