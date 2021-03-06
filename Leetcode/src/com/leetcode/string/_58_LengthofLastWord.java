package com.leetcode.string;

public class _58_LengthofLastWord {

    /**
     *  58. Length of Last Word
     *  when: 2019/03/17
     *  Review1:2019/8/3
     *  review2: 2019/8/24
     *  思路： 通过空格分割成字符数组 然后求最后一个
     *   第一个思路：注意这里分割之后只保留了非空格的部分，所以判断返回0那就直接看length==0
     *   第二个：利用trim进行消除前后多余的空格，然后使用lastIndexOf(" ")找到其index 总长度减去index 再减去一就行了！！
     *  涉及到的数据结构或者方法：
     *  trim() :去掉字符串中首尾多的空格
     *
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;

    }

    public static int lengthOfLastWord2(String s) {
        System.out.println(s.length());
        String[] arr = s.split("\\s+");
        if (arr.length == 0) return 0;
        return arr[arr.length-1].length();
    }
}
