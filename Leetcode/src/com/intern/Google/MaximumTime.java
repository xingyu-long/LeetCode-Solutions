package com.intern.Google;

public class MaximumTime {

    /**
     * Test case:
     * <p>
     * <p>
     * Input: "?4:5?"
     * Output: "14:59"
     * <p>
     * Input: "2?:22"
     * Output: "23:22"
     * <p>
     * Input: "0?:??"
     * Output: "09:59"
     * <p>
     * 第二为数大于3的话，第一位就只能是1
     * 第一位数如果小于2 第二位为？的话 就可以为9
     *
     * @param time
     * @return
     */
    public static String maxTime(String time) {
        if (time == null || time.length() == 0) return null;
        char[] res = "23:59".toCharArray();
        char[] cTime = time.toCharArray();//主要是前面两位
        res[0] = cTime[1] != '?' && cTime[1] > '3' ? '1' : res[0];
        res[1] = cTime[0] != '?' && cTime[0] < '2' ? '9' : res[1];

        // 最后使用原有的cTime来判断，从始至终改变结果的是res
        for (int i = 0; i < time.length(); i++) {
            res[i] = cTime[i] != '?' ? cTime[i] : res[i];
        }
        return time + "=" + new String(res);
    }

    public static String maxTime2(String time) {
        if (time == null || time.length() == 0) return null;
        char[] res = time.toCharArray();

        if (res[0] == '?') {
            res[0] = res[1] != '?' && res[1] > '3' ? '1' : '2';
        }
        if (res[1] == '?') {
            res[1] = res[0] > '1' ? '3' : '9';
        }

        res[3] = (res[3] == '?') ? '5' : res[3];
        res[4] = (res[4] == '?') ? '9' : res[4];
        return time + " = " +new String(res);
    }
    public static void main(String[] args) {
        System.out.println(maxTime2("?4:5?"));
        System.out.println(maxTime2("?4:??"));
        System.out.println(maxTime2("?3:??"));
        System.out.println(maxTime2("23:5?"));
        System.out.println(maxTime2("2?:22"));
        System.out.println(maxTime2("0?:??"));
        System.out.println(maxTime2("1?:??"));
        System.out.println(maxTime2("??:??"));
        System.out.println(maxTime2("?4:0?"));
        System.out.println(maxTime2("?9:4?"));
    }
}
