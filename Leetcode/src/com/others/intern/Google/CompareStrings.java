package com.intern.Google;

public class CompareStrings {

    public static int[] compare (String[] a, String[] b) {
        int[] res = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            int count = 0;
            for (int j = 0; j < a.length; j++) {
//                System.out.println("b[" + i + "]=" + b[i] + "a[" + i + "]=" + b[i]);
                if (isGreater(b[i], a[j])) count++;
            }
            res[i] = count;
        }
        return res;
    }

    public static boolean isGreater(String a, String b) {
        int[] countA = new int[26];
        int[] countB = new int[26];
        int maxA = 0;
        int maxB = 0;
        for (char ch : a.toCharArray()) {
            countA[ch - 'a']++;
            maxA = Math.max(countA[ch - 'a'], maxA);
        }
        for (char ch : b.toCharArray()) {
            countB[ch - 'a']++;
            maxB = Math.max(countB[ch - 'a'], maxB);
        }
        return maxA > maxB;
    }

    public static void main(String[] args) {
        String a = "abcd,aabc,bd";
        String b = "a,aa,abcdeend";
        String[] chA = a.split(",");
        String[] chB = b.split(",");
        int[] res = compare(chA, chB);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
