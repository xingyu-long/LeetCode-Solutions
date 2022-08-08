package com.leetcode.string.count_sort;

public class _87_ScrambleString {

    /**
     * 87. Scramble String
     * When: 2019/03/29
     *
     * solution:
     * 存在两种情况：1. s1和s2进行了相同的处理
     *              2. s1和s2中有的被反转
     *
     * 利用到的数据结构
     * substring(beginIndex, endIndex) / (beginIndex)
     * 以及递归的思想
     * @param s1
     * @param s2
     * @return
     */
    Map<String, Boolean> map = new HashMap<>();
    
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1 == null || s2 == null) return false;
        String key = s1 + " " + s2;
        if (map.containsKey(key)) return map.get(key);
        
        int[] letters = new int[26];
        int len = s1.length();
        // 用来决定s1 s2的字符组成
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }

        //判断s1和s2组成相同，不同的话 肯定不符合
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) return false;
        }

        // 这里要用1，防止死循环，因为为0的话，又会传入原有的字符串进而死循环。
        for (int i = 1; i < len; i++) {
            // 对s1和s2进行同样的分割处理
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) {
                map.put(key, true);
                return true;
            }
            // 对s1和s2进行相反的处理例如 s1 = abcde s2 =edcba
            // 则就是 s1('abc' + 'de'), s2('ed' + 'cba') 这样的分割方式
            if (isScramble(s1.substring(0, i), s2.substring(len - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, len - i))) {
                map.put(key, true);
                return true;
            }
        }
        map.put(key, false);
        return false;
    }
}
