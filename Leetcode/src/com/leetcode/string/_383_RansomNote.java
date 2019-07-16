package com.leetcode.string;

public class _383_RansomNote {

    /**
     *  383.RansomNote
     *  When:2019/7/16
     *  solution: 先记录ransomNote 然后magazine中相同存在的character就--
     *  最后肯定是为负则就可以
     * @param ransomNote
     * @param magazine
     * @return
     */
    // time: O(n), space:O(1)
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];

        for (int i = 0; i < ransomNote.length(); i++) {
            count[ransomNote.charAt(i) - 'a']++;
        }

        for (int i = 0; i < magazine.length(); i++) {
            count[magazine.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) return false;
        }
        return true;
    }

    // more elegant way
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] count = new int[26];

        for (int i = 0; i < magazine.length(); i++) {
            count[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--count[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
