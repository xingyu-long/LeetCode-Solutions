from typing import List

class Solution:
    def primeSubOperation(self, nums: List[int]) -> bool:

        def sieve_of_eratosthenes(n: int) -> List[int]:
            primes = []
            is_primes = [True] * (n + 1)
            is_primes[0] = is_primes[1] = False
            p = 2
            while p * p <= n:
                if is_primes[p]:
                    # same as -> for (int i = p * p; i <= n; i += p)
                    for i in range(p * p, n + 1, p):
                        is_primes[i] = False
                p += 1

            for i in range(2, n + 1):
                if is_primes[i]:
                    primes.append(i)
            return primes

        def find(primes, target):
            # find the largest prime which will strictly smaller than target
            left, right = 0, len(primes) - 1
            while left + 1 < right:
                mid = left + (right - left) // 2
                if primes[mid] < target:
                    left = mid
                else:
                    right = mid
            if primes[right] < target:
                return primes[right]
            if primes[left] < target:
                return primes[left]
            return -1

        primes = sieve_of_eratosthenes(max(nums) + 1)
        for i in range(len(nums)):
            target = nums[i] - (nums[i - 1] if i > 0 else 0)
            largest_prime = find(primes, target)
            # print(f"target = {target}, largest_prime = {largest_prime}")
            if largest_prime != -1:
                nums[i] -= largest_prime
            # print(nums)
            if i > 0 and nums[i] <= nums[i - 1]:
                return False

        return True
