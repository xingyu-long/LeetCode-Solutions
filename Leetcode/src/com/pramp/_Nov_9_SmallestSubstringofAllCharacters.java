package com.pramp;

public class _Nov_9_SmallestSubstringofAllCharacters {
    static String getShortestUniqueSubstring(char[] arr, String str) {
        // your code goes here
        if (arr == null || arr.length == 0) return "";
        int[] counter = new int[128];
        for (char ch : arr) {
            counter[ch]++;
        }
        int start = 0; //
        int end = 0; //
        int num = arr.length;
        String res = ""; // res = str
        int min = Integer.MAX_VALUE;
        // String min = Integer.MAX_VALUE;//
        while (end < str.length()) { //
            if (counter[str.charAt(end)]-- > 0) num--;
            while (num == 0) { // #
                if (end + 1 - start < min) {
                    // if end - start + 1 < min
                    min = end + 1 - start;
                    res = str.substring(start, end + 1); // res , substr
                }
                counter[str.charAt(start)]++;
                if (counter[str.charAt(start)] > 0) num++;
                start++;
            }
            end++;
        }
        return res;
    }

}
