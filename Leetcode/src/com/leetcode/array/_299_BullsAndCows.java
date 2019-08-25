package com.leetcode.array;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class _299_BullsAndCows {

    /**
     * 299. Bulls and Cows
     * When: 2019/3/11-12
     * review1: 2019/7/1
     * review2:2019/8/24
     * @param secret
     * @param guess
     * @return
     */

    // time:O(m) space:O(1)
    public static String getHint(String secret, String guess) {
        if (secret.length() != guess.length()) return "";
        int bull = 0, cow = 0;
        int[] hash = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bull++;
            } else {
                hash[secret.charAt(i) - '0']++;
            }
        }

        for (int i = 0; i < secret.length(); i++) {
            int val = hash[guess.charAt(i) - '0'];
            if (secret.charAt(i) != guess.charAt(i) && val > 0) {
                cow++;
                val--;
            }
        }
        return bull + "A" + cow + "B";
    }

    //利用新的数组来记录其每个数组出现的次数，其中secret++，guess--即可
    public static String getHint2(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] count = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                // 这里的++和--操作都会做 并且先要secret++ 跟上面的原理一样
                if (count[secret.charAt(i) - '0']++ < 0) cows++;
                if (count[guess.charAt(i) - '0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args){
        System.out.println(getHint("1123","0111"));
    }
}
