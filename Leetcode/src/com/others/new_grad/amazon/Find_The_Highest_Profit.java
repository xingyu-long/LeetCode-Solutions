package com.new_grad.amazon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Find_The_Highest_Profit {
    //    public static int maxProfit2(int[] invetory, int order) {
//        Integer[] b = new Integer[invetory.length];
//        for (int i = 0; i < invetory.length; i++) {
//            b = invetory;
//        }
//        Arrays.sort(b, (o1,o2) -> o2-o1);
//        Integer[] a = new Integer[invetory.length+1];
//        for (int i = 0; i < invetory.length; i++) {
//            a = b;
//        }
//        a[invetory.length] = 0;
//        int supIdx = 1;
//        int maxPro = 0;
//        while(order >= 0 && supIdx < a.length) {
//            while(supIdx < a.length && a[supIdx-1] == a[supIdx]) {
//                supIdx++;
//            }//move the pointer to the second largest number in the array and then we will know how many suppliers who have the largest inventory
//            if(a[supIdx-1] == 0) break;
//            int supMulti = supIdx;//供应商数
//            int diff = a[supIdx-1] - a[supIdx];
//            int localCountToOrder = diff * supMulti;
//            localCountToOrder = Math.min(order, localCountToOrder);//how many item we will take at this time
//            order -= localCountToOrder;
//            int localPro = a[supIdx-1];//库存数local provide// 现在最多有多少库存可以用 which is also the profit of each product
//            while(localCountToOrder > 0 && localPro >= a[supIdx]) {//直到second largest price或者order已完成
//                int curCountToTake = Math.min(supMulti, localCountToOrder);
//                maxPro += localPro * curCountToTake;
//                localPro--;
//                localCountToOrder -= curCountToTake;
//            }
//            supIdx++;
//        }
//        return maxPro;
//    }
    // 需要注意PQ的写法可能过不了
    public static int maxProfit(int n, int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            pq.offer(num);
        }
        int res = 0;
        while (k-- > 0 && !pq.isEmpty()) {
            int curr = pq.poll();
            res += curr;
            if (curr > 1) pq.offer(curr - 1);
        }
        return res;
    }

    public static long supplierInventory(int numSupplier, long[] inventory, long order) {
        Map<Long, Long> map = new HashMap<>();
        long highest = 0;
        long res = 0;
        for (int i = 0; i < inventory.length; i++) {
            map.put(inventory[i], map.getOrDefault(inventory[i], 0L) + 1);
            if (highest < inventory[i])
                highest = inventory[i];
        }

        while (order > 0 && !map.isEmpty()) {
            long highestFreq = map.get(highest);
            if (order > highestFreq) {
                res += highestFreq * highest;
                order -= highestFreq;
                map.remove(highest);
                if (map.containsKey(highest - 1)) {
                    long curr = map.get(highest - 1);
                    map.put(highest - 1, map.get(highest - 1) + highestFreq);
                } else
                    map.put(highest - 1, highestFreq);

                highest--;
            } else {
                res += highest * order;
                order = 0;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(supplierInventory(2, new long[]{3, 5}, 6));
        System.out.println(supplierInventory(2, new long[]{3, 5, 7, 10, 6}, 20));
    }
}
