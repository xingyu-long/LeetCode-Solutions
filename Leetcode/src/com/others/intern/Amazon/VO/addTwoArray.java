package com.intern.Amazon.VO;

import java.util.ArrayList;
import java.util.List;

public class addTwoArray {

    public static int[] add(int[] p, int[] q) {
        if (p == null && q == null) return new int[]{};
        if (p == null) return q;
        if (q == null) return p;
        List<Integer> list = new ArrayList<>();
        int i = p.length - 1;
        int j = q.length - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                carry += p[i];
                i--;
            }
            if (j >= 0) {
                carry += q[j];
                j--;
            }
            list.add(carry % 10);
            carry /= 10;
        }
        if (carry != 0) list.add(carry);
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(list.size() - k - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] p = {1};
        int[] q = {9, 9};
        int[] add = add(p, q);
        for (int num : add) {
            System.out.print(num + " ");
        }
    }
}
