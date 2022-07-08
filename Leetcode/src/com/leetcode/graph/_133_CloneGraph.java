package com.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class _133_CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }
    };

     /**
      * When: 2019/06/30, 02/25/2020, 03/10/2020
      * @param node
      * @return
      */
    //time: O(n) space: O(n)
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        Map<Node, Node> map = new HashMap<>();
        return build(node, map);
    }
    
    public Node build(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);
        Node copy = new Node(node.val);
        map.put(node, copy);
        List<Node> nexts = node.neighbors;
        for (Node next : nexts) {
            Node newNext = build(next, map);
            copy.neighbors.add(newNext);
        }
        return map.get(node);
    }

    // BFS, 利用HashMap并且可以做标记。不会添加重复的node。
    public Node cloneGraph2(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        // traversal all nodes 
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, new Node());
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node adj : curr.neighbors) {
                if (!map.containsKey(adj)) {
                    map.put(adj, new Node());
                    queue.offer(adj);
                }
            }
        }
    
        // connect nodes
        for (Node curr : map.keySet()) {
            List<Node> list = new ArrayList<>();
            map.get(curr).val = curr.val;
            for (Node adj : curr.neighbors) {
                list.add(map.get(adj)); // 这里很重要，因为都要是map对应点 
            }
            map.get(curr).neighbors = list;
        }
        return map.get(node);
    }

    // BFS写在一起
    public Node cloneGraph3(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, new Node(node.val));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node neig : curr.neighbors) {
                if (!map.containsKey(neig)) {
                    queue.offer(neig);
                    map.put(neig, new Node(neig.val));
                }
                // 这里稍微注意下 应该都是选对应的情况
                map.get(curr).neighbors.add(map.get(neig));
            }
        }
        return map.get(node);
    }
}
