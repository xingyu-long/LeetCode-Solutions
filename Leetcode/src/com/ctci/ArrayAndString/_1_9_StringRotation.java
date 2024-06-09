package com.ctci.ArrayAndString;

public class _1_9_StringRotation {
    /**
     * 2019/8/18
     *
     * StringRotation:Assume you have a method isSubstring which checks if one word is a substring of
     * another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
     * (e.g., "waterbottle" is a rotation of" erbottlewat").
     *
     * solution:
     * 由于通过s1反转为s2,然后s1s1肯定会有s2（如果是有rotation）
     * @param s
     * @param t
     * @return
     */
    public boolean isSubstring(String s, String t) {
        return (s.length() == t.length()) && ((s + s).contains(t));
    }
}
