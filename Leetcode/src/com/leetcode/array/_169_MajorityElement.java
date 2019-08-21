package com.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;

public class _169_MajorityElement {

    /**
     * 169. Majority Element
     * when: 2019/03/17
     * Review1: 2019/7/4
     * review2: 2019/8/20
     *
     * 思路： 下面两种solutions
     *
     * 涉及到的数据结构或者方法：
     * HashMap<>
     *
     * @param nums
     * @return
     */
    // time:O(n) space:O(n)
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }

            if (map.get(num) > nums.length / 2) {
                res = num;
                break;
            }
        }
        return res;
    }

    // solution2: 由于题目规定了一定会有大于n/2的情况，则目标数就在最中间！
    // time:O(nlogn) space:O(1)
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // Moore voting algorithm
    // https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
    // solution 3: 利用count，一开始计算第一个的num存在的count 然后与后面比较
    // 相同就+1 不同就-1 并且导致res 取到后面。。。依次进行
    // 每次都找出一对不同的元素，从数组中删掉，直到数组为空或只有一种元素。
    // 不难证明，如果存在元素e出现频率超过半数，那么数组中最后剩下的就只有e。
    // time:O(n) space:O(1)
    public int majorityElement3(int[] nums) {
        int count = 0;
        int res = 0;
        for (int num : nums) {
            if (count == 0)
                res = num;
            if (res == num) count++;
            else count--;
        }
        return res;
    }

}
