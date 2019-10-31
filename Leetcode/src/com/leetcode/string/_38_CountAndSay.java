package com.leetcode.string;

public class _38_CountAndSay {

    /**
     * 38. Count and Say
     * 题目依然理解不懂
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 依次去数上面的个数特征 例如1 表示1 第二个则是表示有一个1 表示为11，然后第三个则就是2个1表示为21
     * When: 2019/04/01
     * review1: 2019/8/7
     * Test case:
     * n = 1 （默认）
     *
     * n = 2  res = "1" count = 0; c = "1" res.length() = 1
     *        j = 0 (0 != 1 && res.charAt(0) == "1")
     *        count = 1
     *        j = 1 进行else语句 sb = "11" res = "11"
     *
     * n = 3 res = "11" count = 0; c="1" res.length() = 2
     *       j = 0 (0 != 2 && res.charAt(0) == "1" )
     *       count = 1
     *       j = 1 (1 != 2 && res.charAt(1) == "1" )
     *       count = 2
     *       j = 2 (2 == 2) 执行else
     *       sb = "21" res = "21"
     *
     * n = 4 res = "21" count = 0; c = "2" res.length() = 2
     *       j = 0 (0 != 2 && res.charAt(0) == "2")
     *       count = 1
     *       j = 1 (1 != 2 && res.charAt(1) != "2") 执行else sb = "12"
     *             (1 != 2) count = 1; c = "1"  这一步很重要 相当于重置比较的数字（因为这个字符与前面的不同，所以需要重新统计）
     *       j = 2 (2 == 2) 执行else sb = "1211"
     *       res = "1211"
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        // 从第一个开始
        int i = 1;
        String res = "1";
        while (i < n) {
            int count = 0; // 用来计当前的字符的数目
            StringBuilder sb = new StringBuilder();
            char c = res.charAt(0); // 获取第一个数作为比较的字符
            // 这里设置为<= 只是为了把最后一次结果加入进去，因为我们这里是从前往后看
            for (int j = 0; j <= res.length(); j++) {
                if (j != res.length() && res.charAt(j) == c) {
                    count++;
                } else {
                    //加入当前的sb里面（表示已经计数 两种情况，j==res.length 或者res.charAt(j) != c）
                    sb.append(count);
                    sb.append(c);
                    if (j != res.length()) {
                        count = 1;
                        c = res.charAt(j);
                    }
                }
            }
            res = sb.toString();
            i++;
        }
        return res;
    }
}
