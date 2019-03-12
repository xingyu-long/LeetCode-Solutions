package com.leetcode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class BullsAndCows {

    /**
     * LeetCode No.299 Bulls and Cows
     * Time: 2019/03/11-12
     * @param secret
     * @param guess
     * @return
     */
    public static String getHint(String secret, String guess) {
        if (secret.length() == 0 || guess.length() == 0) return "0A0B";
        // 计算 Bull
        int bull = 0;
        // 计算 cow
        int cow = 0;
        // 保存secret中与guess不同的数组
        ArrayList<Character> map = new ArrayList<>();
        // 记录相同的元素
        ArrayList<Character> arr = new ArrayList<>();
        for (int i = 0; i < secret.length(); i++){
            if (secret.charAt(i) == guess.charAt(i)) {
                bull++;
                arr.add(secret.charAt(i));
            }
            else {
                map.add(secret.charAt(i));
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
            if(! arr.contains(temp)){
                if(map.contains(temp)){
                    cow++;
                    int index = map.indexOf(temp);
                    map.remove(index);
                }
            }else{
                //防止重复使用
                int index = arr.indexOf(temp);
                arr.remove(index);
            }
        }
        return bull+"A"+cow+"B";
    }

    public static void main(String[] args){
        System.out.println(getHint("1123","0111"));
    }
}
