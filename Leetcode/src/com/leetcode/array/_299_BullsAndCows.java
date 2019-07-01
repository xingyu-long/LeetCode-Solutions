package com.leetcode.array;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class _299_BullsAndCows {

    /**
     *  299. Bulls and Cows
        When: 2019/3/11-12
        review1: 2019/7/1
     * @param secret
     * @param guess
     * @return
     */

    //time: O(n) space: O(n)
    public static String getHint(String secret, String guess) {
        if (secret.length() == 0 || guess.length() == 0) return "0A0B";
        // 计算 Bull
        int bull = 0;
        // 计算 cow
        int cow = 0;
        // 保存secret中与guess不同的数组
        ArrayList<Character> diff = new ArrayList<>();
        // 记录相同的元素
        ArrayList<Character> same = new ArrayList<>();
        for (int i = 0; i < secret.length(); i++){
            if (secret.charAt(i) == guess.charAt(i)) {
                bull++;
                same.add(secret.charAt(i));
            }
            else {
                diff.add(secret.charAt(i));
            }
        }
        /**
         * 1. 利用guesss数组反着寻找
         * 2. 排除已经相同（为bull元素）
         * 3. arr含有temp就删除一个
         * 4. map里面含有temp的话 就删除一个元素并且cow+1
         */
        for (int i = 0; i < guess.length(); i++){
            char temp = guess.charAt(i);
            if(! same.contains(temp)){
                if(diff.contains(temp)){
                    cow++;
                    int index = diff.indexOf(temp);
                    diff.remove(index);
                }
            }else{
                //防止重复使用
                int index = same.indexOf(temp);
                same.remove(index);
            }
        }
        return bull+"A"+cow+"B";
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
