class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!uf.union(edge[0]-1, edge[1]-1)) {
                return edge;
            }
        }
        return new int[] {0, 1};
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    int sets;

    UnionFind(int n) {
        this.sets = n;
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
    }

    int find(int i) {
        if (i == this.parent[i]) {
            return i;
        }
        this.parent[i] = find(this.parent[i]);
        return this.parent[i];
    }

    boolean union(int i, int j) {
        int parentI = find(i),
            parentJ = find(j);
        
        if (parentI == parentJ) {
            return false;
        }

        if (this.rank[parentI] > this.rank[parentJ]) {
            this.parent[parentJ] = parentI;
        } else if (this.rank[parentI] < this.rank[parentJ]) {
            this.parent[parentI] = parentJ;
        } else {
            this.parent[parentJ] = parentI;
            this.rank[parentI]++;
        }
        this.sets--;
        return true;
    }
}
