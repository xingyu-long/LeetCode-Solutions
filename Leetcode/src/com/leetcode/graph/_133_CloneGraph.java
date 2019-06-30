package com.leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class _133_CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }
    };
    private HashMap<Node, Node> map = new HashMap<>();

    /**
     *  133. Clone Graph
        When: 2019/06/30

        solution:
        DFS
     * @param node
     * @return
     */
    //time: O(n) space: O(n)
    public Node cloneGraph(Node node) {
        return clone(node);
    }

    private Node clone(Node node) {
        if (node == null) return null;

        if (! map.containsKey(node)) {
            Node newNode = new Node(node.val);
            map.put(node, newNode);
            List<Node> neighbors = new LinkedList<>();
            for (int i = 0; i < node.neighbors.size(); i++) {
                neighbors.add(clone(node.neighbors.get(i)));
            }
            newNode.neighbors = neighbors;
        }
        return map.get(node);
    }
}
