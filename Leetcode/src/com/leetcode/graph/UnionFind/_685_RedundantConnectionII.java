package com.leetcode.graph.UnionFind;

public class _685_RedundantConnectionII {
    public class UF {
        int[] id;
        int[] size;
        
        public UF (int n) {
            id = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }    
            return p;
        }
        
        public boolean union(int p, int q) {
            int pid = find(p);
            int qid = find(q);
            if (pid == qid) return false;
            if (size[pid] > size[qid]) {
                size[pid] += size[qid];
                id[qid] = pid;
            } else {
                size[qid] += size[pid];
                id[pid] = qid;
            }
            return true;
        }
    }
    
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int numNodes = edges.length, edgeRemoved = -1, edgeMakesCycle = -1;
        int[] parent = new int[numNodes + 1];
        
        for (int i = 0; i < numNodes; i++) {
            int u = edges[i][0], v = edges[i][1];
            if (parent[v] != 0) {
                // it has dual parents and it should be removed.
                edgeRemoved = i;
            } else {
                parent[v] = u;
            }
        }
        
        UF uf = new UF(numNodes);
        for (int i = 0; i < numNodes; i++) {
            if (i == edgeRemoved) {
                continue;
            }
            int u = edges[i][0], v = edges[i][1];
            if (!uf.union(u, v)) {
                edgeMakesCycle = i;
                break;
            }
        }
        
        // No dual parents and just remove edge that causes cycle.
        if (edgeRemoved == -1) {
            return edges[edgeMakesCycle];
        }
        // dual parents and cycle
        if (edgeMakesCycle != -1) {
            int v = edges[edgeRemoved][1];
            int u = parent[v];
            return new int[]{u, v};
        }
        // dual parents and need to remove edge that causes the second parent
        return edges[edgeRemoved];
    }
}
