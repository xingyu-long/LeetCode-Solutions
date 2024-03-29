package com.leetcode.stack_priority_queue;

import java.util.*;

public class _332_ReconstructItinerary {

    public static HashMap<String, PriorityQueue<String>> map;
    public static List<String> res;

    //虽然这个算是有环的，但是每次操作都删除了一个点，最后还是有路径的。
    // 理解成那个后序遍历比较好，
    public static List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<>();
        res = new LinkedList<>();
        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(ticket.get(1));
                map.put(ticket.get(0), pq);
            } else {
                map.get(ticket.get(0)).add(ticket.get(1));
            }
            //  简写但是不好理解
            //  map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        helper("JFK");
        return res;
    }

    public static void helper(String airport) {
        while (map.containsKey(airport) && !map.get(airport).isEmpty()) {
            helper(map.get(airport).poll());
        }
        res.add(0, airport);
    }

    // 也可以看看这个扩展思路
    // https://leetcode.com/problems/reconstruct-itinerary/discuss/78799/Very-Straightforward-DFS-Solution-with-Detailed-Explanations

    // 利用stack辅助，也是dfs的想法。
    public List<String> findItinerary2(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> res = new LinkedList<>();
        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(ticket.get(1));
                map.put(ticket.get(0), pq);
            } else {
                map.get(ticket.get(0)).add(ticket.get(1));
            }
        }
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            while (map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) {
                stack.push(map.get(stack.peek()).poll());
            }
            res.add(0, stack.pop());
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));
        findItinerary(tickets);
    }

}
