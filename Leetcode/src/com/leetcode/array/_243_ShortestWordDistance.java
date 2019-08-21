package com.leetcode.array;

public class _243_ShortestWordDistance {
    /**
     * 243. Shortest Word Distance
     * When: 2019/03/18
     * Review1: 2019/7/4
     * review2: 2019/8/20
     */
    // solution2: 依然是n*n 的解法，具体思路跟上面一致，只是更加简单
    // time: O(n^2) space: O(1)
    public static int shortestDistance1(String[] words, String word1, String word2){
        int res = words.length;
        for (int i = 0; i < words.length; i++){
            if (words[i].equals(word1)){
                for (int j = 0; j < words.length; j++){
                    if (words[j].equals(word2)){
                        res = Math.min(res, Math.abs(i - j));
                    }
                }
            }
        }
        return res;
    }

    /**
     * solution3： 依然用获取其位置后然后比较的方式
     *
     * space: O(1)
     * time: O(n)
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public static int shortestDistance2(String[] words, String word1, String word2) {
        int res = words.length;
        int a = -1;
        int b = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                a = i;
            } else if (words[i].equals(word2)) {
                b = i;
            }

            if (a != -1 && b != -1) {
                res = Math.min(res, Math.abs(a - b));
            }
        }
        return res;
    }

    public static void main(String[] args){
        String[] words = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "makes";
        System.out.println(shortestDistance2(words, word1, word2));
    }
}
