package com.pramp;

public class BusiestTimeInTheMall {
    static int findBusiestPeriod(int[][] data) {
        // your code goes here
        int timeStamp = data[0][0];
        int cur = 0;
        int maxCur = 0;
        // 向前看比较
        for (int i = 0; i < data.length; i++) {

            if (data[i][2] == 1) {
                cur += data[i][1];
            } else if (data[i][2] == 0) {
                cur -= data[i][1];
            }

            if (i == data.length - 1 || data[i][0] != data[i + 1][0]) {
                if (maxCur < cur) {
                    timeStamp = data[i][0];
                    maxCur = cur;
                }
            }
        }
        return timeStamp;
    }

    /*
    [ [1487799425,14,1], cur = 14; max = 14;
      [1487799425,4,1],  cur = 18; max = 18;
      [1487799425,2,1],  cur = 20; max = 20;
      [1487800378,10,1],  cur = 30; max = 30;
      [1487801478,18,1],  cur = 40; max = 40;
      [1487901013,1,1], cur = 41, max = 41;
      [1487901211,7,1], cur = 48, max = 48
      [1487901211,7,1]  cur = 55 max = 55;
      ]

    */
    public static void main(String[] args) {
        int[][] data = {{1487799425,14,1},
                {1487799425,4,1},
                {1487799425,2,1},
                {1487800378,10,1},
                {1487801478,18,1},
                {1487901013,1,1},
                {1487901211,7,1},
                {1487901211,7,1}};
        System.out.println(findBusiestPeriod(data));
    }
}
