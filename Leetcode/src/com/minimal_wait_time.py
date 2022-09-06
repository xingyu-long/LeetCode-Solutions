'''
Date: 09/04/2022 21:53:36
LastEditTime: 09/05/2022 10:33:12
Description: heapq, simulation
'''
import heapq


"""
银行有多个agency服务，他们分别有不同服务时间，比如[2,5,3]，
然后你前面有n个人，求要等到多少时间后才轮到自己。用queue来模拟做就好了。跟其他面经类似

Example:
比如有3个银行柜台，它们功能一样，但是每个柜台的的服务一个人的耗时不同，分别为[2,5,3]分钟；
然后你前面有4个人，那么你需要等到前面4个人都被服务完才轮到你，所以最快的应该就是3分钟后
（因为在3分钟时刻，2分钟速度的服务员能够消耗服务2个人，5分钟速度的服务员在消耗服务1个人，
3分钟速度的服务员消耗服务完成1个人，然后下一个就是轮到你了。）
"""


def wait_time(nums: list, k: int) -> int:
    heap = []
    res = 0
    nums.sort()
    d = dict()
    for num in nums:
        d[num] = False
    i = 0
    while i < k:
        available = False
        for num in nums:
            # already been used
            if d[num]:
                continue
            heapq.heappush(heap, [num, num])
            d[num] = True
            available = True
            print('i = {}, service_time = {} is available'.format(i, num))
            i += 1
            break
        if not available:
            # wait for some time
            min_time, service_time = heapq.heappop(heap)
            print('No available desk for {} and have to wait {}'.format(i, min_time))
            d[service_time] = False
            removed_list_idx = []
            for idx in range(len(heap)):
                heap[idx][0] -= min_time
                if heap[idx][0] == 0:
                    # it ends in same time
                    d[heap[idx][1]] = False
                    removed_list_idx.append(idx)

            for x in removed_list_idx:
                del heap[x]
            heapq.heapify(heap)
            res += min_time

    return res


"""
  #2,  #3,  #5
  
1st -> #2 (2min)
2nd -> #3 (3min)
3nd -> #5 (5min)

4nd -> wait  res = 2
1st-> pop -> #2 available
4th -> #2 (2min)
#3 (1min)
#5 (3min)

5th -> pop -> #3 available res = 2 + 1 = 3
4th #2 (1min)
5th #3 (3min)
3th #5 (2min)

"""


def main():
    nums = [2, 3, 5]
    k = 20
    print(wait_time(nums, k))


if __name__ == '__main__':
    main()
