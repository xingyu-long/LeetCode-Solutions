package com.intern.GoldmanSachs;

import java.util.*;

public class StrangeSort {

    public static void main(String[] args) {
        List<String> nums = Arrays.asList("990", "332", "32");
        List<Integer> lookup = Arrays.asList(3,5,4,6,2,7,9,8,0,1);
        List<String> res = helper(nums, lookup);
        for (String s : res) {
            System.out.println(s);
        }
    }

    private static List<String> helper(List<String> nums, List<Integer> lookup) {
        Map<Integer, Integer> map = new HashMap<>();
        List<String> res =  new ArrayList<>();
        String[][] newNums = new String[nums.size()][2];// 0 保存原来的，1保存现在的

        //得到lookup里面对应的index
        for (int i = 0; i < lookup.size(); i++) {
            map.put(lookup.get(i), i);
        }

        // 记录现在对应的数值
        for (int i = 0; i < nums.size(); i++) {
            String cur = nums.get(i);
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < cur.length(); j++) {
                sb.append((char)('0' + map.get(cur.charAt(j) - '0')));
            }
            newNums[i][0] = cur;
            newNums[i][1] = sb.toString();
        }
        Arrays.sort(newNums, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });

        for (int i  = 0; i < nums.size(); i++) {
            res.add(newNums[i][0]);
        }
        return res;
    }
}
