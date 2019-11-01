package com.intern.MathWork;

public class TravelIsFun {
    static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
        // Complete this function
        int[] root = new int[n + 1];
        int[] des = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            root[i] = i;
            des[i] = 1;
        }
        for (int i = g + 1; i <= n; i++) {
            for (int j = 2 * i; j <= n; j += i) {
                union(j, i, root, des);
            }
        }
        int[] res = new int[originCities.length];
        int i = 0, j = 0, k = 0;
        while (i < originCities.length && j < destinationCities.length) {
            if (getRoot(originCities[i], root) == getRoot(destinationCities[j], root)) {
                res[k] = 1;
            } else {
                res[k] = 0;
            }
            i++;j++;k++;
        }
        return res;
    }

    private static void union(int a, int b, int[] root, int[] des) {
        int rootA = getRoot(a, root);
        int rootB = getRoot(b, root);
        if (rootA == rootB) {
            return;
        }
        if (des[rootA] < des[rootB]) {
            root[rootA] = root[rootB];
            des[rootB] += des[rootA];
        } else {
            root[rootB] = root[rootA];
            des[rootA] += des[rootB];
        }
    }

    private static int getRoot(int num, int[] root) {
        while (num!=root[num]){
            num = root[num];
        }
        return num;
    }
}
