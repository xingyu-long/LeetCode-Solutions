package com.leetcode.string;

import java.util.Arrays;
import java.util.Comparator;

public class _179_LargestNumber {

    private static class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    /**
     * 179. Largest Number
     * When: 2019/03/29
     * review1:2019/8/7
     * solution:
     * 主要利用重写sort函数的意义进行操作（让能够组成越大的字符串放在前面）
     *
     * 涉及到的数据结构：
     * @param nums
     * @return
     */
    // time:O(nlogn) space:O(n)
    public static String largestNumber(int[] nums) {
        // 主要考察关于重写compare的问题，两个数连接之后不同的比较
        //用来保存上面的int数组
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // 利用自制的comparator 进行比较
        Arrays.sort(strs, new LargerNumberComparator());

        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
        // 如果排序后的最大的数为0 则就返回0
        if (strs[0].equals("0")) {
            return "0";
        }

        //拼装其字符串
        String res = new String();
        for (String str: strs){
            res += str;
        }

        return res;
    }

    public static void main(String[] args){
        int nums[] = new int[]{3,30,34,5,8,11};
        largestNumber(nums);
    }
}
