package com.EPI.String;

public class ReplaceAndRemove {

    // ***PASS***
//    Consider the following two rules that are to be applied to an array of characters.
//      •Replace each 'a' by two 'd's.
//      •Delete each entry containing a 'b'.
//    For example, applying these rules to the array (a,c,d,b,b,c,a)
//    results in the array (d, d, c, d, c, d, d)
    public static int replaceAndRemove(int size, char[] s) {
        // first pass: remove 'b's and count 'a'  forward
        int writeIndex = 0, aCount = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] != 'b') {
                s[writeIndex++] = s[i];
            }
            if (s[i] == 'a')  aCount++;
        }
        // second pass: replace 'a' with two 'd's backward
        int total = writeIndex + aCount - 1;
        int finalSize = total + 1;
        int curIndex = writeIndex - 1;
        while (curIndex >= 0) {
            if (s[curIndex] == 'a') {
                s[total--] = 'd';
                s[total--] = 'd';
            } else {
                s[total--] = s[curIndex];
            }
            curIndex--;
        }
        return finalSize;
    }
}
