package com.intern.Bloomberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTwoList {

    // 从末尾开始做
    public static List<Integer> addTwoList (List<Integer> a, List<Integer> b) {
        int carry = 0;
        // 每次结果加到前面
        List<Integer> res = new ArrayList<>();
        while (a.size() != 0 && b.size() != 0) {
            int sum  = a.get(a.size() - 1) + b.get(b.size() - 1) + carry;
            res.add(0, sum % 10);
            carry = sum / 10;
            a.remove(a.size() -1);
            b.remove(b.size() - 1);
        }

        while (a.size() != 0) {
            res.add(0, a.get(a.size() - 1));
            a.remove(a.size() - 1);
        }
        while (b.size() != 0) {
            res.add(0, b.get(b.size() - 1));
            b.remove(b.size() - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<Integer> b = new ArrayList<>(Arrays.asList(3,4));
        List<Integer> res = addTwoList(a, b);
        System.out.println(res.toString());
    }
}
