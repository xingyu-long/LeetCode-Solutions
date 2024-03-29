class Solution {
    class Data {
        long freq;
        int idx;

        public Data(long freq, int idx) {
            this.freq = freq;
            this.idx = idx;
        }
    }
    // time: O(nlogn)
    // space: O(n)
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> (Long.compare(b.freq, a.freq)));
        Map<Integer, Long> map = new HashMap<>(); // num -> freq
        int n = nums.length;
        long[] res = new long[n];
        
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0L) + freq[i]);
            pq.offer(new Data(map.get(nums[i]), nums[i]));
            // make sure we pop out the stale data
            while (!pq.isEmpty() && pq.peek().freq != map.get(pq.peek().idx)) {
                pq.poll();
            }
            res[i] = pq.peek().freq;
        }
        return res;
    }
}