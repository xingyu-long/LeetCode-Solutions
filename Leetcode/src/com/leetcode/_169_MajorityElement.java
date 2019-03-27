package com.leetcode;

import java.util.HashMap;

public class _169_MajorityElement {

    /**
     * 169. Majority Element
     * when: 2019/03/17
     *
     * 思路： 下面两种solutions
     *
     * 涉及到的数据结构或者方法：
     * HashMap<>
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        /**
         * solution1: 由于题目规定了一定会有大于n/2的情况，则目标数就在最中间！
         * Arrays.sort(nums);
         * return nums[nums.length / 2];
         */

        /**
         * solution2: 使用hashmap<Integer, Integer>进行，
         *             然后没出现的就插入，出现过一次然后出现就累加value值
         *            刚刚出错就是因为hashmap多打了() 然后需要有return！ 这里也需要break
         */

        /**
         * solution3: 利用Morre voting algorithm （只正对于有n/2的数字存在）
         * 思路：利用count，一开始计算第一个的num存在的count 然后与后面比较 相同就+1
         * 不同就-1 并且导致res 取到后面。。。依次进行
         *      int count = 0;
         *      int res = 0;
         *      for (int num: nums){
         *          if (count == 0)
         *              res = num;
         *          if (res == num) count++;
         *          else count --;
         *      }
         *      return res
         */
        HashMap<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for (int num: nums){
            if (!map.containsKey(num)){
                map.put(num, 1);
            }else{
                map.put(num, map.get(num) + 1);
            }

            if(map.get(num) > nums.length / 2){
                res = num;
                break;
            }
        }
        return res;
    }


}
