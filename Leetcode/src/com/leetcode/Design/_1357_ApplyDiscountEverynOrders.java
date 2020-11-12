package com.leetcode.design;

import java.util.HashMap;

public class _1357_ApplyDiscountEverynOrders {
    // 1357. Apply Discount Every n Orders
    // when: 02/22/2020
    // time:O(n) space:O(n)
    public int count;
    public int n;
    public int dis;
    public HashMap<Integer, Integer> map;
    public _1357_ApplyDiscountEverynOrders(int n, int discount, int[] products, int[] prices) {
        count = 0;
        this.n = n;
        dis = discount;
        map = new HashMap<>();
        for (int i = 0; i < products.length; i++) {
            map.put(products[i], prices[i]);
        }
    }

    public double getBill(int[] product, int[] amount) {
        count++;
        double res = 0;
        for (int i = 0; i < product.length; i++) {
            res += map.get(product[i]) * amount[i];
        }
        if (count % n == 0) return res - res * (dis) / 100;
        return res;
    }
}
