package com.leetcode.string;

public class _6_ZigZagConversion {

    /**
     *  6. ZigZag Conversion
     *  when: 2019/03/29
     *  Review1:2019/8/2
     *  solution:
        主要找出来坐标的规律，注意反向的时候
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        // init StringBuilder
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        // 数学规律 即每个字母出现在 2 * numRows - 2这个循环里 （但是要注意反向的部分，则就是2 * numRows - 2 - index）
        for (int i = 0; i < s.length(); i++) {
            int index = i % (2 * numRows - 2);
            index = index >= numRows ? 2 * numRows - 2 - index : index;
            System.out.println(index);
            System.out.println(s.charAt(i));
            sb[index].append(s.charAt(i));
        }

        //拼接字符
        for (int i = 1; i < sb.length; i++) {
            sb[0].append(sb[i]);
        }
        return sb[0].toString();
    }

    public static void main(String[] args){
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
    }
}
